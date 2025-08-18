package com.example.Centralthon.domain.route.model;
import java.util.Map;

// 보행 경로 행렬 (Pedestrian Matrix)
public record PedMatrix(
        double[][] distMatrix, // 비용(거리/시간) 테이블
        Map<SegmentKey, PedSegment> segments) {
}
