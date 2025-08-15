package com.example.Centralthon.domain.menu.repository;

import com.example.Centralthon.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.time.LocalDateTime;
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    // BoundingBox 안에 존재하는 모든 메뉴 반환
    @Query(value = """
        SELECT m.* FROM menus m
        JOIN stores s ON m.store_id = s.store_id
        WHERE s.latitude BETWEEN :minLat AND :maxLat AND s.longitude BETWEEN :minLng AND :maxLng
          AND ST_Distance_Sphere(POINT(s.longitude, s.latitude), POINT(:lng, :lat)) <= 2000
          AND m.quantity > 0 AND m.deadline > :now
        """, nativeQuery = true)
    List<Menu> findNearbyMenus(
            @Param("lat") double lat,
            @Param("lng") double lng,
            @Param("now") LocalDateTime now,
            @Param("minLat") double minLat,
            @Param("maxLat") double maxLat,
            @Param("minLng") double minLng,
            @Param("maxLng") double maxLng
    );

    // 해당 가게에서 판매 하는 메뉴 반환
    @Query("""
    SELECT m FROM Menu m
    JOIN FETCH m.store s
    WHERE s.id = :storeId AND m.quantity > 0 AND m.deadline > :now
    """)
    List<Menu> findByStoreId(
            @Param("storeId") Long storeId,
            @Param("now") LocalDateTime now);

    /**
     * ids에 포함된 Menu와 연관된 Store를 한번에 가져옴
     */
    @Query(value = "SELECT m from Menu m join fetch m.store where m.id in :ids")
    List<Menu> findAllByIdWithStore(@Param("ids") Collection<Long> ids);

}
