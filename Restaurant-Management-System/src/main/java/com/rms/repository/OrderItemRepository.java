package com.rms.repository;

import com.rms.entity.OrderItem;
import com.rms.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Find all items for an order
    List<OrderItem> findByOrder(Order order);
}
