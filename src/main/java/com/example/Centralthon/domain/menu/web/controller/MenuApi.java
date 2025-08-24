package com.example.Centralthon.domain.menu.web.controller;

import com.example.Centralthon.domain.menu.web.dto.*;
import com.example.Centralthon.global.external.ai.web.dto.GetTipReq;
import com.example.Centralthon.global.external.ai.web.dto.GetTipRes;
import com.example.Centralthon.global.response.SuccessResponse;
import com.example.Centralthon.global.validation.Latitude;
import com.example.Centralthon.global.validation.Longitude;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Menus", description = "반찬 관련 API")
public interface MenuApi {
    @Operation(
            summary = "맞춤 추천용 메뉴 목록 조회",
            description ="사용자 위치 기준 2km 이내에 가게에서 판매중인 반찬 매물을 반환합니다.<br>"+
                    "중복을 제외하고 { 반찬명, 카테고리 }를 반환합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "맞춤 추천용 메뉴 목록 조회 성공",
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
                      "data": [
                        {
                            "name": "진미채 볶음",
                            "category" : "STIR_FRY"
                        },
                        {
                            "name": "두부 조림",
                            "category" : "BRAISED"
                        }
                      ],
                      "isSuccess": true
                    }
                    """
                    )
            )
    )
    ResponseEntity<SuccessResponse<List<NearbyMenusRes>>> nearbyMenus(
            @Parameter(name = "lat", description = "사용자 위도", example = "37.468355", required = true)
            @RequestParam("lat") @Latitude Double lat,
            @Parameter(name = "lng", description = "사용자 경도", example = "127.039073", required = true)
            @RequestParam("lng") @Longitude Double lng);

    @Operation(
            summary = "특정 메뉴 판매 가게 조회",
            description = "반찬명을 기준으로 해당 메뉴를 판매하는 가게를 조회합니다. 공백까지 포함하여 동일 여부를 판단합니다.<br>"+
                    "{ 메뉴Id값, 가게명, 사용자-가게 거리(km), 할인가, 수량}을 반환합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "특정 메뉴 판매 가게 조회 성공",
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
                      "data": [
                            {
                                "menuId": 15,
                                "storeName": "초록찬 비건키친",
                                "distance": 1.7047542239779305,
                                "salePrice": 4000,
                                "quantity": 7
                            },
                            {
                                "menuId": 1,
                                "storeName": "우찬이네 반찬",
                                "distance": 1.0348092111962985,
                                "salePrice": 4200,
                                "quantity": 10
                            }
                      ],
                      "isSuccess": true
                    }
                    """
                    )
            )
    )
    ResponseEntity<SuccessResponse<List<StoresByMenuRes>>> storesByMenu(
            @Parameter(name = "name", description = "메뉴 이름(공백 포함하여 완전일치)", example = "두부 조림", required = true)
            @RequestParam("name") String name,
            @Parameter(name = "lat", description = "사용자 위도", example = "37.468355", required = true)
            @RequestParam("lat") @Latitude Double lat,
            @Parameter(name = "lng", description = "사용자 경도", example = "127.039073", required = true)
            @RequestParam("lng") @Longitude Double lng);

    @Operation(
            summary = "메뉴 상세 조회",
            description = "사용자가 추가한 각 메뉴들의 상세 정보를 반환합니다.<br>"+
                "{메뉴Id값, 메뉴 이름, 카테고리, 원가, 할인가, 할인율, 가게명}을 반환합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "메뉴 상세 조회 성공",
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
                      "data": [
                        {
                            "menuId": 1,
                            "name": "진미채 볶음",
                            "category" : "STIR_FRY",
                            "costPrice" : 3000,
                            "salePrice" : 2400,
                            "salePercent" : 20,
                            "storeName" : "우찬이네 밥상"
                        },
                        {
                            "menuId": 4,
                            "name": "두부 조림",
                            "category" : "BRAISED",
                            "costPrice" : 5000,
                            "salePrice" : 4000,
                            "salePercent" : 20,
                            "storeName" : "희망 식당"
                        }
                      ],
                      "isSuccess": true
                    }
                    """
                    )
            )
    )
    ResponseEntity<SuccessResponse<List<MenuDetailsRes>>> details(@RequestBody @Valid MenuIdsReq menus);



    @Operation(
            summary = "메뉴 Tip 조회",
            description = "입력한 메뉴 이름 배열을 기반으로 AI 추천 Tip(활용법/조리법 등)을 반환합니다.<br>" +
                    "{title, content} 리스트를 반환합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "메뉴 Tip 조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "SUCCESS_200",
                            value = """
                    {
                        "timestamp": "2025-08-22 03:11:13",
                        "code": "SUCCESS_200",
                        "httpStatus": 200,
                        "message": "호출에 성공하였습니다.",
                        "data": [
                            {
                                "title": "콩나물의 변신, 국물 한 스푼",
                                "content": "콩나물무침을 육수에 넣고 끓이면 시원한 콩나물국이 됩니다."
                            },
                            {
                                "title": "감자볶음, 크리스피한 감자튀김으로",
                                "content": "감자볶음을 잘게 썰어 튀김가루에 묻혀서 튀기면 바삭한 감자튀김이 됩니다."
                            },
                            {
                                "title": "애호박 볶음, 달콤한 애호박전으로",
                                "content": "애호박볶음을 반죽에 섞어 팬에 부치면 맛있는 애호박전이 됩니다."
                            }
                        ],
                        "isSuccess": true
                    }
                    """
                    )
            )
    )
    ResponseEntity<SuccessResponse<List<GetTipRes>>> getTips(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "메뉴 이름 배열 요청",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = GetTipReq.class),
                            examples = @ExampleObject(
                                    value = """
                            {
                                "menus": ["콩나물무침", "감자볶음", "애호박볶음"]
                            }
                            """
                            )
                    )
            )
            @Valid @RequestBody GetTipReq getTipReq);

    @Operation(
            summary = "메뉴 추천",
            description = "사용자 위치(latitude, longitude)와 컨셉(concept)을 기반으로 맞춤 메뉴를 추천합니다.<br>" +
                    "concept는 다음 중 하나만 선택할 수 있습니다: `diet`, `keto`, `low_sodium`, `bulking`, `glycemic`"
    )
    @ApiResponse(
            responseCode = "200",
            description = "메뉴 추천 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "SUCCESS_200",
                            value = """
                    {
                        "timestamp": "2025-08-22 14:36:04",
                        "code": "SUCCESS_200",
                        "httpStatus": 200,
                        "message": "호출에 성공하였습니다.",
                        "data": [
                            {
                                "name": "돼지 목살 스테이크",
                                "category": "STIR_FRY"
                            },
                            ...
                        ],
                        "isSuccess": true
                    }
                    """
                    )
            )
    )
    ResponseEntity<SuccessResponse<List<NearbyMenusRes>>> recommend(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "사용자 위치와 추천 컨셉 요청",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = GetRecommendedMenusReq.class),
                            examples = @ExampleObject(
                                    value = """
                            {
                              "latitude": 37.4752,
                              "longitude": 127.050,
                              "concept": "keto",
                              "count": 15
                            }
                            """
                            )
                    )
            )
            @Valid @RequestBody GetRecommendedMenusReq getRecommendedMenusReq);

}
