package com.example.Centralthon.global.external.ai.service;

import com.example.Centralthon.global.external.ai.web.dto.GetMenusByThemeReq;
import com.example.Centralthon.global.external.ai.web.dto.GetMenusByThemeRes;
import com.example.Centralthon.global.external.ai.web.dto.GetTipReq;
import com.example.Centralthon.global.external.ai.web.dto.GetTipRes;

public interface AiService {
    GetTipRes getTip(GetTipReq getTipReq);
    GetMenusByThemeRes getMenuByTheme(GetMenusByThemeReq getMenuByThemeReq);
}
