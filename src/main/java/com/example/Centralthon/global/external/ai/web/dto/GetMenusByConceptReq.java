package com.example.Centralthon.global.external.ai.web.dto;

import com.example.Centralthon.domain.menu.entity.enums.Concept;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GetMenusByConceptReq {
    Concept concept;
    int count;
    List<String> items;
}
