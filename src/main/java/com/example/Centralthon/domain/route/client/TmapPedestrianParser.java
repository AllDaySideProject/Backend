package com.example.Centralthon.domain.route.client;

import com.example.Centralthon.domain.route.model.PedSegment;
import com.example.Centralthon.domain.route.web.dto.LocationRes;
import com.example.Centralthon.global.util.geo.GeoUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TmapPedestrianParser {
    public PedSegment parsePedestrian(Map<String, Object> pedRes) {
        long totalDistance = 0L;
        long totalDuration = 0L;
        List<LocationRes> path = new ArrayList<>();

        List<Map<String, Object>> features =
                (List<Map<String, Object>>) pedRes.getOrDefault("features", List.of());

        // 1) 총거리/시간 추출
        for (Map<String, Object> f : features) {
            Map<String, Object> props = (Map<String, Object>) f.get("properties");
            if (props == null) continue;

            Object d = props.get("totalDistance");
            long candDist = asLong(d);
            if (candDist > 0) totalDistance = candDist;

            Object t = props.get("totalTime");
            long candTime = asLong(t);
            if (candTime > 0) totalDuration = candTime;
            if (totalDistance > 0 && totalDuration > 0) break;
        }

        // 2) LineString 경로 좌표 이어붙이기 (중복점 제거)
        for (Map<String, Object> f : features) {
            Map<String, Object> geom = (Map<String, Object>) f.get("geometry");
            if (geom == null) continue;
            String type = String.valueOf(geom.get("type"));
            if (!"LineString".equalsIgnoreCase(type)) continue;

            List<List<Number>> coords = (List<List<Number>>) geom.get("coordinates");
            if (coords == null) continue;

            for (List<Number> xy : coords) {
                if (xy.size() < 2) continue;
                double x = xy.get(0).doubleValue(); // lon
                double y = xy.get(1).doubleValue(); // lat
                LocationRes p = new LocationRes(x, y); // (lng, lat)

                if (path.isEmpty()) {
                    path.add(p);
                } else {
                    LocationRes last = path.get(path.size() - 1);
                    if (Double.compare(last.lng(), p.lng()) != 0 || Double.compare(last.lat(), p.lat()) != 0) {
                        path.add(p);
                    }
                }
            }
        }
        // 3) 총거리 누락 시 하버사인 합으로 보정
        if (totalDistance <= 0 && path.size() >= 2) {
            totalDistance = Math.round(GeoUtils.sumHaversineMeters(path));
        }

        return new PedSegment(totalDistance, totalDuration, path);
    }

    private static long asLong(Object v) {
        if (v == null) return 0L;
        if (v instanceof Number n) return n.longValue();
        if (v instanceof String s && !s.isBlank()) {
            try { return Long.parseLong(s.trim()); } catch (NumberFormatException ignored) {}
        }
        return 0L;
    }
}
