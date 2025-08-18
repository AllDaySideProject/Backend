package com.example.Centralthon.domain.route.web.dto;
import java.util.*;

public record RouteRes(
        long distanceMeters,
        List<Long> optimizedStoreIds,
        String polyline
){}
