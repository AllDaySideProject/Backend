package com.example.Centralthon.global.external.ai.service;

import com.example.Centralthon.global.external.ai.web.dto.GetMenusByThemeReq;
import com.example.Centralthon.global.external.ai.web.dto.GetMenusByThemeRes;
import com.example.Centralthon.global.external.ai.web.dto.GetTipReq;
import com.example.Centralthon.global.external.ai.web.dto.GetTipRes;

import java.util.List;

public interface AiService {
    List<GetTipRes> getTipFromAi(GetTipReq getTipReq);
    GetMenusByThemeRes getMenuByThemeFromAi(GetMenusByThemeReq getMenuByThemeReq);
}
