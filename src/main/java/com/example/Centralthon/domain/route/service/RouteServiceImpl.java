package com.example.Centralthon.domain.route.service;

import com.example.Centralthon.domain.route.algo.PathStitcher;
import com.example.Centralthon.domain.route.algo.TspSolver;
import com.example.Centralthon.domain.route.model.CombinedPath;
import com.example.Centralthon.domain.route.model.PedMatrix;
import com.example.Centralthon.domain.route.web.dto.LocationRes;
import com.example.Centralthon.domain.route.web.dto.RouteReq;
import com.example.Centralthon.domain.route.web.dto.RouteRes;
import com.example.Centralthon.domain.store.entity.Store;
import com.example.Centralthon.domain.store.exception.StoreNotFoundException;
import com.example.Centralthon.domain.store.repository.StoreRepository;
import com.example.Centralthon.global.util.geo.GeoUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final StoreRepository storeRepository;
    private final PedMatrixBuilder pedMatrixBuilder;

    @Override
    @Transactional(readOnly = true)
    public RouteRes findOptimalPath(RouteReq req) {
        // 0) 입력 검증 및 노드 생성 (0 = 사용자, 1..n = 가게)
        List<Store> stores = storeRepository.findAllById(req.getStoreIds());
        if (stores.isEmpty()) throw new StoreNotFoundException();

        List<LocationRes> nodes = new ArrayList<>();
        nodes.add(new LocationRes(req.getUserLng(), req.getUserLat()));
        nodes.addAll(stores.stream()
                .map(s -> new LocationRes(s.getLongitude(), s.getLatitude()))
                .toList());

        // 1) 보행자 경로 행렬/세그먼트 사전 계산
        PedMatrix pm = pedMatrixBuilder.build(nodes);

        // 2) TSP 라우팅
        List<Integer> order = TspSolver.solveOpenTour(pm.distMatrix());

        // 3) 세그먼트 조합 → 전체 경로 좌표/거리/시간 합산
        CombinedPath combined = PathStitcher.stitch(order, pm.segments());

        // 4) 좌표 리스트를 폴리라인 인코딩
        String polyline = combined.path().isEmpty() ? "" : GeoUtils.encodePolyline(combined.path());

        // 5) storeId 순서 변환
        List<Long> idOrder = order.stream()
                .filter(i -> i != 0)
                .map(i -> stores.get(i - 1).getId())
                .toList();

        return new RouteRes(
                combined.distanceMeters(),
                idOrder,
                polyline
        );
    }
}