package com.example.Centralthon.global.util.geo;

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
}
