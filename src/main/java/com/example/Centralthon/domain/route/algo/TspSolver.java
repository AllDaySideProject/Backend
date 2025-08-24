package com.example.Centralthon.domain.route.algo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TspSolver {

    public static List<Integer> solveOpenTour(double[][] d) {
        int k = d.length;
        if (k <= 1) {
            return IntStream.range(0, k).boxed().toList(); // [ ] or [0]
        }

        // 초기화 작업
        boolean[] used = new boolean[k];
        List<Integer> path = new ArrayList<>();
        int cur = 0;
        used[0] = true;
        path.add(0);

        // 최근접 이웃 초기 해 구성
        for (int step = 1; step < k; step++) {
            int best = -1;
            double bestD = Double.POSITIVE_INFINITY;
            for (int j = 1; j < k; j++) if (!used[j]) {
                double cand = d[cur][j];
                if (cand < bestD) {
                    bestD = cand;
                    best = j;
                }
            }
            used[best] = true;
            path.add(best);
            cur = best;
        }

        return twoOpt(path, d);
    }

    // 교차 제거를 통한 로컬 개선
    private static List<Integer> twoOpt(List<Integer> tour, double[][] d) {
        boolean improved = true;
        int n = tour.size();
        while (improved) {
            improved = false;
            outer:
            for (int i = 1; i < n - 2; i++) {
                for (int k = i + 1; k < n - 1; k++) {
                    double delta =
                            - d[tour.get(i - 1)][tour.get(i)]
                                    - d[tour.get(k)][tour.get(k + 1)]
                                    + d[tour.get(i - 1)][tour.get(k)]
                                    + d[tour.get(i)][tour.get(k + 1)];
                    if (delta < -1e-6) {
                        Collections.reverse(tour.subList(i, k + 1));
                        improved = true;
                        break outer;
                    }
                }
            }
        }
        return tour;
    }
}
