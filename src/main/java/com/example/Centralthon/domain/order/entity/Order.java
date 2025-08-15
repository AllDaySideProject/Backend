package com.example.Centralthon.domain.order.entity;

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

    @Column(name = "pick_up_code", nullable = false, unique = true, length = 6)
    private String pickUpCode;

    @Column(nullable = false)
    private int price;

    @Column(name = "is_picked_up", nullable = false)
    private boolean pickedUp;

    public static Order toEntity(String pickUpCode, int price){
        return Order.builder()
                .pickUpCode(pickUpCode)
                .price(price)
                .pickedUp(false)
                .build();
    }

    public void completeOrder(){
        this.pickedUp = true;
    }
}
