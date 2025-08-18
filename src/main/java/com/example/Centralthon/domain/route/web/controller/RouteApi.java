package com.example.Centralthon.domain.route.web.controller;

import com.example.Centralthon.domain.route.web.dto.RouteReq;
import com.example.Centralthon.domain.route.web.dto.RouteRes;
import com.example.Centralthon.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Routes", description = "최적 경로 API")
public interface RouteApi {
    @Operation(
            summary = "최적 경로 생성",
            description = "사용자 위치와 경유지를 기반으로 최적 경로를 생성합니다. 경로는 폴리라인으로 인코딩되어 넘어갑니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "최적 경로 생성 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "SUCCESS_200",
                            value = """
                    {
                      "timestamp": "2025-08-15 04:22:54",
                      "code": "SUCCESS_200",
                      "httpStatus": 200,
                      "message": "호출에 성공하였습니다.",
                      "data": {
                        "distanceMeters": 10423,
                        "optimizedStoreIds": [
                            5,
                            2,
                            1,
                            4
                        ],
                        "polyline": "gfkdFeuefW\\\\OZKj@INDF@DEN[B?Rg@BB`@ZPXDNNx@@L?PCPCLAHhAcARHN@EfAIl@ADY~ACLMx@@FB@t@LhB`@LFn@h@XLL@F@tAFT@BBN?B?LEXELCDAFCD?LAn@EJ??ABA@?DAP?HA@?DC@FNr@P|"
                      },
                      "isSuccess": true
                    }
                    """
                    )
            )
    )
    ResponseEntity<SuccessResponse<RouteRes>> findOptimalPath(@Valid @RequestBody RouteReq req);
}
