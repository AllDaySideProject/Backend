package com.example.Centralthon.domain.order.repository;

import com.example.Centralthon.domain.order.entity.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
