package com.example.Centralthon.global.external.ai.web.dto;

import java.util.List;

public record GetMenusByConceptWrapper(
        String concept,
        int count,
        List<GetMenusByConceptRes> items
) {}
