package com.example.Centralthon.domain.menu.service;

import com.example.Centralthon.domain.menu.web.dto.*;
import com.example.Centralthon.domain.menu.web.dto.NearbyMenusRes;
import com.example.Centralthon.domain.menu.web.dto.StoresByMenuRes;
import java.util.List;

public interface MenuService {
    List<NearbyMenusRes> nearbyMenus(double latitude, double longitude);

    List<StoresByMenuRes> storesByMenu(String name, double lat, double lng);

    List<MenuDetailsRes> details(MenuIdsReq menus);
}
