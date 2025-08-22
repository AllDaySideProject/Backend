package com.example.Centralthon.global.external.ai.service;

import com.example.Centralthon.global.external.ai.web.dto.GetMenusByConceptReq;
import com.example.Centralthon.global.external.ai.web.dto.GetMenusByConceptRes;
import com.example.Centralthon.global.external.ai.web.dto.GetTipReq;
import com.example.Centralthon.global.external.ai.web.dto.GetTipRes;

import java.util.List;

public interface AiService {
    List<GetTipRes> getTipFromAi(GetTipReq getTipReq);
    List<GetMenusByConceptRes> getMenuByConceptFromAi(GetMenusByConceptReq getMenuByThemeReq);
}
