package com.example.Centralthon.global.external.ai.client;

import com.example.Centralthon.global.external.exception.AiCommunicationFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AiClient {
    private final RestTemplate restTemplate;

    public <T> T postForObject(String url, Object request, Class<T> responseType) {
        try{
            return restTemplate.postForObject(url, request, responseType);
        } catch (Exception e){
            throw new AiCommunicationFailedException();
        }

    }
}
