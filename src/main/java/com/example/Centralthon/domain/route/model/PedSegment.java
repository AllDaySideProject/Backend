package com.example.Centralthon.domain.route.model;

import com.example.Centralthon.domain.route.web.dto.LocationRes;
import java.util.List;

// 보행 경로 세그먼트 (두 지점 A→B 사이)
public record PedSegment(
        long distance,
        long duration,
        List<LocationRes> path // A -> B까지의 보행 경로 좌표 리스트
) {}
