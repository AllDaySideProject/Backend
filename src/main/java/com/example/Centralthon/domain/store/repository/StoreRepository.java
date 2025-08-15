package com.example.Centralthon.domain.store.repository;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query(value =  """ 
            SELECT DISTINCT s.* FROM stores s
            JOIN menus m ON m.store_id = s.store_id
            WHERE s.latitude BETWEEN :minLat AND :maxLat AND s.longitude BETWEEN :minLng AND :maxLng
            AND ST_Distance_Sphere(POINT(s.longitude, s.latitude), POINT(:lng, :lat)) <= 2000
            AND m.quantity > 0 AND m.deadline > :now
            """, nativeQuery = true)
    List<Store> findNearbyStores(
            @Param("lat") double lat,
            @Param("lng") double lng,
            @Param("now") LocalDateTime now,
            @Param("minLat") double minLat,
            @Param("maxLat") double maxLat,
            @Param("minLng") double minLng,
            @Param("maxLng") double maxLng
    );
}
