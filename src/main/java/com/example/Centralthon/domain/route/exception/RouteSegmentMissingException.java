package com.example.Centralthon.domain.route.exception;

import com.example.Centralthon.global.exception.BaseException;

public class RouteSegmentMissingException extends BaseException {
    public RouteSegmentMissingException() {
        super(RouteErrorCode.ROUTE_SEGMENT_MISSING);
    }
}
