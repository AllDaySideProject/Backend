package com.example.Centralthon.domain.store.entity;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.store.entity.enums.StoreCategory;
import com.example.Centralthon.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STORES")
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;

    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    private double latitude;
    private double longitude;

    @OneToMany(mappedBy ="store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menus = new ArrayList<>();
}
