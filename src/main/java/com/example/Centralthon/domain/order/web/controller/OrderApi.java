package com.example.Centralthon.domain.order.web.controller;

import com.example.Centralthon.domain.order.web.dto.CompleteOrderReq;
import com.example.Centralthon.domain.order.web.dto.CreateOrderReq;
import com.example.Centralthon.domain.order.web.dto.CreateOrderRes;
import com.example.Centralthon.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Orders", description = "주문/픽업 관련 API")
public interface OrderApi {

    @Operation(
            summary = "주문 생성",
            description = "픽업 예약을 생성합니다. 완료 시 픽업 코드와 주문한 가게들의 기본키를 반환합니다."
    )
    @ApiResponse(
            responseCode = "201",
            description = "주문 생성 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "SUCCESS_201",
                            value = """
                    {
                      "timestamp": "2025-08-15 04:22:54",
                      "code": "SUCCESS_201",
                      "httpStatus": 201,
                      "message": "호출에 성공하였습니다.",
                      "data": {
                        "code": "PE8512",
                        "storeList": [1, 3]
                      },
                      "isSuccess": true
                    }
                    """
                    )
            )
    )
    ResponseEntity<SuccessResponse<CreateOrderRes>> createOrder(CreateOrderReq orderReq);

    @Operation(
            summary = "픽업 완료 처리",
            description = "픽업 코드를 사용하여 주문을 픽업 완료로 처리합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "픽업 완료 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "SUCCESS_200",
                            value = """
                    {
                      "timestamp": "2025-08-15 04:45:14",
                      "code": "SUCCESS_200",
                      "httpStatus": 200,
                      "message": "호출에 성공하였습니다.",
                      "data": null,
                      "isSuccess": true
                    }
                    """
                    )
            )
    )
    ResponseEntity<SuccessResponse<?>> completePickUp(CompleteOrderReq completeOrderReq);
}