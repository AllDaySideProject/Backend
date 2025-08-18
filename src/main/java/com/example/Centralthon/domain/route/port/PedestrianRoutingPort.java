package com.example.Centralthon.domain.route.port;

import com.example.Centralthon.domain.route.model.PedSegment;
import com.example.Centralthon.domain.route.web.dto.LocationRes;
import reactor.core.publisher.Mono;

public interface PedestrianRoutingPort {
    Mono<PedSegment> fetchSegment(LocationRes a, LocationRes b);
}
