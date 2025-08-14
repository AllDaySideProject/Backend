package com.example.Centralthon.domain.store.repository;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query(value = "SELECT s.* FROM stores s " +
            "WHERE s.latitude BETWEEN :minLat AND :maxLat AND s.longitude BETWEEN :minLng AND :maxLng " +
            "AND ST_Distance_Sphere(POINT(s.longitude, s.latitude), POINT(:lng, :lat)) <= 2000 " +
            "AND EXISTS ( " +
            "    SELECT 1 FROM menus m " +
            "    WHERE m.store_id = s.store_id AND m.quantity > 0 AND m.deadline > NOW())"
            , nativeQuery = true)
    List<Store> findNearbyStores(
            double lat,
            double lng,
            LocalDateTime now,
            double minLat,
            double maxLat,
            double minLng,
            double maxLng
    );
}
