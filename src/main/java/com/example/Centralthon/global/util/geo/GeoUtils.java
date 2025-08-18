package com.example.Centralthon.global.util.geo;

import com.example.Centralthon.domain.route.web.dto.LocationRes;

import java.util.List;

public class GeoUtils {
    private static final double R = 6371.0; // 지구 반경 (km)

    // 거리 반경 지정
    public static BoundingBox calculateBoundingBox(double lat, double lng, double radiusKm) {
        // Bounding Box 계산 로직
        double latRadius = Math.toDegrees(radiusKm / R);
        double lngRadius = Math.toDegrees(radiusKm / R / Math.cos(Math.toRadians(lat)));

        double minLat = lat - latRadius;
        double maxLat = lat + latRadius;
        double minLng = lng - lngRadius;
        double maxLng = lng + lngRadius;

        return new BoundingBox(minLat, maxLat, minLng, maxLng);
    }

    // 거리 계산
    public static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    // 간단한 하버사인 합산
    public static double sumHaversineMeters(List<LocationRes> pts) {
        double sum = 0.0;
        for (int i = 1; i < pts.size(); i++) {
            sum += calculateDistance(
                    pts.get(i - 1).lat(), pts.get(i - 1).lng(),
                    pts.get(i).lat(),     pts.get(i).lng()
            );
        }
        return sum;
    }

    // ====== Polyline Encode (Google format, precision 1e-5) ======
    public static String encodePolyline(List<LocationRes> path) {
        return encodePolyline(path, 1e5);
    }

    public static String encodePolyline(List<LocationRes> path, double precision) {
        if (path == null || path.isEmpty()) return "";

        long factor = Math.round(precision);
        long lastLat = 0L;
        long lastLng = 0L;
        StringBuilder sb = new StringBuilder();

        for (LocationRes p : path) {
            // LocationRes는 (lng, lat) 순서 필드 — 인코딩은 (lat, lng) 순서로
            long lat = Math.round(p.lat() * factor);
            long lng = Math.round(p.lng() * factor);

            long dLat = lat - lastLat;
            long dLng = lng - lastLng;

            encodeValue(dLat, sb);
            encodeValue(dLng, sb);

            lastLat = lat;
            lastLng = lng;
        }
        return sb.toString();
    }

    private static void encodeValue(long v, StringBuilder out) {
        // ZigZag + varint (Google polyline 규칙)
        v = (v < 0) ? ~(v << 1) : (v << 1);
        while (v >= 0x20) {
            int chunk = (int)((0x20 | (v & 0x1F)) + 63);
            out.append((char) chunk);
            v >>= 5;
        }
        out.append((char)(v + 63));
    }
}
