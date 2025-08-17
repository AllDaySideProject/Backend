package com.example.Centralthon.domain.route.service;

import com.example.Centralthon.domain.route.exception.RouteNotCreatedException;
import com.example.Centralthon.domain.route.model.*;
import com.example.Centralthon.domain.route.port.PedestrianRoutingPort;
import com.example.Centralthon.domain.route.web.dto.LocationRes;
import com.example.Centralthon.global.util.geo.GeoUtils;
import io.netty.handler.timeout.ReadTimeoutException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class PedMatrixBuilder {
    private final PedestrianRoutingPort routingPort;

    private static final Duration PER_CALL_TIMEOUT = Duration.ofSeconds(3);
    private static final int      RETRIES     = 3;
    private static final Duration RETRY_DELAY = Duration.ofMillis(250);

    public PedMatrix build(List<LocationRes> nodes) {
        // 전체 노드 수 (0 = 사용자, 1..n = 가게)
        int k = nodes.size();

        // 도보 거리 행렬 (대칭행렬)
        double[][] dist = new double[k][k];

        // (i,j) 쌍별 실제 보행자 경로 세그먼트 (거리/시간/좌표)
        Map<SegmentKey, PedSegment> segs = new ConcurrentHashMap<>();

        List<Mono<Void>> calls = new ArrayList<>();

        // 폴백(하버사인)으로 만든 세그먼트 개수
        AtomicInteger fallbackCount = new AtomicInteger(0);

        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                // 람다 캡처 에러 방지: 루프 변수의 로컬 복사본
                final int ii = i, jj = j;
                LocationRes a = nodes.get(ii);
                LocationRes b = nodes.get(jj);

                Mono<Void> call = routingPort.fetchSegment(a, b)
                        .timeout(PER_CALL_TIMEOUT)
                        // 일시적 오류만 3회 재시도
                        .retryWhen(reactor.util.retry.Retry
                                .fixedDelay(RETRIES, RETRY_DELAY)
                                .filter(this::isTransientError))
                        // 실패 시 하버사인 폴백
                        .onErrorResume(ex -> {
                            fallbackCount.incrementAndGet();
                            double dMeter = GeoUtils.calculateDistance(a.lat(), a.lng(), b.lat(), b.lng());
                            return Mono.just(new PedSegment(Math.round(dMeter), 0L, List.of(a, b)));
                        })
                        .doOnNext(seg -> {
                            SegmentKey key = SegmentKey.of(ii, jj);
                            segs.put(key, seg);
                            dist[ii][jj] = dist[jj][ii] = seg.distance();
                        })
                        .then();

                calls.add(call);
            }
        }
        Mono.when(calls).block();

        // 모든 세그먼트가 폴백 시 예외 처리
        int totalCalls = calls.size();
        if (totalCalls > 0 && fallbackCount.get() == totalCalls) {
            throw new RouteNotCreatedException();
        }

        return new PedMatrix(dist, segs);
    }

    /** 타임아웃/네트워크/5xx 같은 일시적 오류만 재시도 대상으로 */
    private boolean isTransientError(Throwable t) {
        if (t instanceof TimeoutException) return true;
        if (t instanceof ReadTimeoutException) return true;
        if (t instanceof WebClientRequestException) return true;
        if (t instanceof WebClientResponseException w) {
            return w.getStatusCode().is5xxServerError();
        }
        return false;
    }
}
