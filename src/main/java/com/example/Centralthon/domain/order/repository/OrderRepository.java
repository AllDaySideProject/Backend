package com.example.Centralthon.domain.order.repository;

import com.example.Centralthon.domain.order.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findByPickUpCode(String pickUpCode);
}
