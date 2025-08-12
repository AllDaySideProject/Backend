package com.example.Centralthon.domain.menu.repository;

import com.example.Centralthon.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query(value = "SELECT m.* FROM menus m JOIN stores s ON m.store_id = s.store_id WHERE " +
            "s.latitude BETWEEN :minLat AND :maxLat " +
            "AND s.longitude BETWEEN :minLng AND :maxLng " +
            "AND ST_Distance_Sphere(POINT(s.longitude, s.latitude), POINT(:lng, :lat)) <= 2000 " +
            "AND m.quantity > 0 " +
            "AND m.deadline > :now", nativeQuery = true)
    List<Menu> findNearbyMenus(
            double lat,
            double lng,
            LocalDateTime now,
            double minLat,
            double maxLat,
            double minLng,
            double maxLng
    );

}
