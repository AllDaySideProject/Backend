package com.example.Centralthon.domain.menu.service;

import com.example.Centralthon.domain.menu.web.dto.NearbyMenusRes;
import com.example.Centralthon.domain.menu.web.dto.StoresByMenu;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface MenuService {
    List<NearbyMenusRes> nearbyMenus(double latitude, double longitude);

    List<StoresByMenu> storesByMenu(String name, double lat, double lng);
}
