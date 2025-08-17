package com.example.Centralthon.domain.route.model;

// 경로 행렬의 키 (두 노드 간 구간을 고유하게 식별)
public record SegmentKey(
        int i,
        int j) {
    public SegmentKey {
        if (i > j) {
            int tmp = i;
            i = j;
            j = tmp;
        }
    }

    public static SegmentKey of(int a, int b) {
        return new SegmentKey(a, b);
    }
}
