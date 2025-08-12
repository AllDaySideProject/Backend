package com.example.Centralthon.global.util.geo;

public class GeoUtils {
    private static final double R = 6371.0; // 지구 반경 (km)

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
}
