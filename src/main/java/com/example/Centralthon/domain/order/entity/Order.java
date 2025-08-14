package com.example.Centralthon.domain.order.entity;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @Column(nullable = false)
    private String pickUpCode;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private boolean isPickedUp;
}
