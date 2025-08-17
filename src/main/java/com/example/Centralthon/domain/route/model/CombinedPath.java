package com.example.Centralthon.domain.route.model;

import com.example.Centralthon.domain.route.web.dto.LocationRes;
import java.util.List;

// 최종 합성 경로 (사용자 출발 → 경유지들 → 도착)
public record CombinedPath(
        long distanceMeters,
        long durationSeconds,
        List<LocationRes> path) {
}
