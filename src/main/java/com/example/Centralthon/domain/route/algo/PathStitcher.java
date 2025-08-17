package com.example.Centralthon.domain.route.algo;
import com.example.Centralthon.domain.route.exception.RouteSegmentMissingException;
import com.example.Centralthon.domain.route.model.*;
import com.example.Centralthon.domain.route.web.dto.LocationRes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PathStitcher {

    public static CombinedPath stitch(List<Integer> order, Map<SegmentKey, PedSegment> segs) {
        long distSum = 0, durSum = 0;
        List<LocationRes> merged = new ArrayList<>();

        for (int t = 0; t < order.size() - 1; t++) {
            int i = order.get(t), j = order.get(t + 1);

            PedSegment seg = segs.get(SegmentKey.of(i, j));
            if (seg == null) throw new RouteSegmentMissingException();

            List<LocationRes> p = seg.path();
            if (merged.isEmpty()) merged.addAll(p);
            else {
                if (!p.isEmpty() && !merged.isEmpty()
                        && equalsPoint(merged.get(merged.size()-1), p.get(0))) {
                    merged.addAll(p.subList(1, p.size()));
                } else merged.addAll(p);
            }
            distSum += seg.distance();
            durSum  += seg.duration();
        }
        return new CombinedPath(distSum, durSum, merged);
    }

    // 좌표 중복 여부 확인
    private static boolean equalsPoint(LocationRes a, LocationRes b) {
        return Double.compare(a.lng(), b.lng()) == 0 && Double.compare(a.lat(), b.lat()) == 0;
    }
}
