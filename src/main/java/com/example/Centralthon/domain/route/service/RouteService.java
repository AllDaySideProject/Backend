package com.example.Centralthon.domain.route.service;

import com.example.Centralthon.domain.route.web.dto.RouteReq;
import com.example.Centralthon.domain.route.web.dto.RouteRes;

public interface RouteService {
    RouteRes findOptimalPath( RouteReq req);
}
