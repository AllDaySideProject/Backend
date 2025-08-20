package com.example.Centralthon.global.external.ai.service;

import com.example.Centralthon.global.external.ai.web.dto.GetMenusByThemeReq;
import com.example.Centralthon.global.external.ai.web.dto.GetMenusByThemeRes;
import com.example.Centralthon.global.external.ai.web.dto.GetTipReq;
import com.example.Centralthon.global.external.ai.web.dto.GetTipRes;
import org.springframework.stereotype.Service;

@Service
public class AiServiceImpl implements AiService {

    @Override
    public GetTipRes getTip(GetTipReq getTipReq) {
        return null;
    }

    @Override
    public GetMenusByThemeRes getMenuByTheme(GetMenusByThemeReq getMenuByThemeReq) {
        return null;
    }
}
