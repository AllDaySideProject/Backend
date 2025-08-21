package com.example.Centralthon.global.external.ai.service;

import com.example.Centralthon.global.external.ai.web.dto.GetMenusByThemeReq;
import com.example.Centralthon.global.external.ai.web.dto.GetMenusByThemeRes;
import com.example.Centralthon.global.external.ai.web.dto.GetTipReq;
import com.example.Centralthon.global.external.ai.web.dto.GetTipRes;
import com.example.Centralthon.global.external.response.ExternalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {

    private final RestTemplate restTemplate;

    @Value("${AI_SERVER_URL}")
    private String baseUrl;

    @Override
    public List<GetTipRes> getTipFromAi(GetTipReq getTipReq) {
        String tipUrl = baseUrl + "tip";
        ResponseEntity<ExternalResponse<List<GetTipRes>>> response = restTemplate.exchange(
                tipUrl,
                HttpMethod.POST,
                new HttpEntity<>(getTipReq),
                new ParameterizedTypeReference<ExternalResponse<List<GetTipRes>>>() {}
        );

        return response.getBody().data();
    }

    @Override
    public GetMenusByThemeRes getMenuByThemeFromAi(GetMenusByThemeReq getMenuByThemeReq) {
        return null;
    }
}
