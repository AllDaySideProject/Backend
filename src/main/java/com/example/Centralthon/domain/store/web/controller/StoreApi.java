package com.example.Centralthon.domain.store.web.controller;

import com.example.Centralthon.domain.order.web.dto.CompleteOrderReq;
import com.example.Centralthon.domain.order.web.dto.CreateOrderReq;
import com.example.Centralthon.domain.order.web.dto.CreateOrderRes;
import com.example.Centralthon.domain.store.web.dto.NearbyStoresRes;
import com.example.Centralthon.domain.store.web.dto.StoreMenusRes;
import com.example.Centralthon.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Stores", description = "가게 관련 API")
public interface StoreApi {
    @Operation(
            summary = "근처 가게 위치 목록 조회",
            description = "사용자 위치 기준 2km 반경 내에 가게 목록을 조회합니다.<br> 가게들의 기본키와 좌표값(위도, 경도)을 반환합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "근처 가게 조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "SUCCESS_200",
                            value = """
                    {
                      "timestamp": "2025-08-14 15:54:04",
                      "code": "SUCCESS_200",
                      "httpStatus": 200,
                      "message": "호출에 성공하였습니다.",
                      "data": [
                        {
                            "storeId": 1,
                            "lat": 37.59,
                            "lng": 127.0164
                        },
                        {
                            "storeId": 2,
                            "lat": 37.577,
                            "lng": 127.0204
                        }
                      ],
                      "isSuccess": true
                    }
                    """
                    )
            )
    )
    ResponseEntity<SuccessResponse<List<NearbyStoresRes>>> nearbyStores(
            @Parameter(name = "lat", description = "사용자 위도", example = "37.468355", required = true)
            @RequestParam("lat") Double lat,
            @Parameter(name = "lng", description = "사용자 경도", example = "127.039073", required = true)
            @RequestParam("lng") Double lng);

    @Operation(
            summary = "가게에서 판매 중인 메뉴 조회",
            description ="현재 재고가 있고 마감 기한을 넘지 않은 메뉴들의 목록을 반환합니다. distacne = 사용자와 가게간의 거리(km)<br>" +
                        "{ 메뉴 Id값, 이름, 카테고리, 원가, 할인가, 할인율, 수량} 을 반환합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "판매 메뉴 목록 조회 성공",
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
                      "data": {
                        "name": "오색퓨전찬",
                        "category": "FUSION_SIDE_DISH",
                        "distance": 1.0419556581490452,
                        "menus": [
                            {
                                "menuId": 4,
                                "name": "진미채 볶음",
                                "category": "STIR_FRY",
                                "costPrice": 5000,
                                "salePrice": 4500,
                                "salePercent": 10,
                                "quantity": 7
                            },
                            {
                                "menuId": 6,
                                "name": "순두부찌개",
                                "category": "SOUP",
                                "costPrice": 6200,
                                "salePrice": 5900,
                                "salePercent": 5,
                                "quantity": 4
                            }
                        ]
                      },
                      "isSuccess": true
                    }
                    """
                    )
            )
    )
    ResponseEntity<SuccessResponse<StoreMenusRes>> getStoreMenus(
            @Parameter(name = "storeId", in = ParameterIn.PATH, description = "가게 ID", example = "1", required = true)
            @PathVariable Long storeId,
            @Parameter(name = "lat", in = ParameterIn.QUERY, description = "사용자 위도", example = "37.468355", required = true)
            @RequestParam("lat") Double lat,
            @Parameter(name = "lng", in = ParameterIn.QUERY, description = "사용자 경도", example = "127.039073", required = true)
            @RequestParam("lng") Double lng);
}
