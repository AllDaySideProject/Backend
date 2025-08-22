package com.example.Centralthon.global.external.ai.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetMenusByConceptRes(
        @JsonProperty("input_menu") String inputMenu,
        @JsonProperty("matched_name") String matchedName,
        float similarity,
        float suitability
) {
}
