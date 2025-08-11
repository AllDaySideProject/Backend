package com.example.Centralthon.domain.menu.entity;

import com.example.Centralthon.domain.menu.entity.enums.MenuCategory;
import com.example.Centralthon.domain.store.entity.Store;
import com.example.Centralthon.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MENUS")
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int costPrice;
    private int salePrice;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private MenuCategory category;

    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
