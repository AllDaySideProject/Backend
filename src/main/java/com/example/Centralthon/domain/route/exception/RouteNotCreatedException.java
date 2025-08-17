package com.example.Centralthon.domain.route.exception;

import com.example.Centralthon.global.exception.BaseException;

public class RouteNotCreatedException extends BaseException {
    public RouteNotCreatedException() {
        super(RouteErrorCode.ROUTE_NOT_CREATED);
    }
}
