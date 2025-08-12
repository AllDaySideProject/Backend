package com.example.Centralthon.domain.menu.service;

import com.example.Centralthon.domain.menu.web.dto.NearbyMenusRes;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface MenuService {
    List<NearbyMenusRes> nearbyMenus(double latitude, double longitude);
}
