package com.example.Centralthon.domain.route.client;

import com.example.Centralthon.domain.route.model.PedSegment;
import com.example.Centralthon.domain.route.port.PedestrianRoutingPort;
import com.example.Centralthon.domain.route.web.dto.LocationRes;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TmapPedestrianClient implements PedestrianRoutingPort {
    private final WebClient tmapWebClient;
    private final TmapPedestrianParser parser;

    @Override
    public Mono<PedSegment> fetchSegment(LocationRes a, LocationRes b) {
        // 1. Tmap 보행자 경로 API 요청 바디 생성
        Map<String, Object> body = Map.of(
                "startX", String.valueOf(a.lng()),
                "startY", String.valueOf(a.lat()),
                "endX",   String.valueOf(b.lng()),
                "endY",   String.valueOf(b.lat()),
                "reqCoordType", "WGS84GEO",
                "resCoordType", "WGS84GEO",
                "startName", "S", "endName", "E"
        );

        // 2. API 호출 → JSON 응답 수신 → parser로 PedSegment 변환
        return tmapWebClient.post()
                .uri(ub -> ub.path("/tmap/routes/pedestrian").queryParam("version","1").build())
                .bodyValue(body)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {})
                .map(parser::parsePedestrian);
    }
}
