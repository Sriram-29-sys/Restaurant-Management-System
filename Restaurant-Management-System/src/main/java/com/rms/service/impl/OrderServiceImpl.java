package com.rms.service.impl;

import com.rms.entity.Order;
import com.rms.entity.TableEntity;
import com.rms.repository.OrderRepository;
import com.rms.service.OrderService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByTable(TableEntity table) {
        return orderRepository.findByTable(table);
    }

    @Override
    public List<Order> getOrdersByStatus(Order.Status status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public Order updateOrderStatus(Long id, Order.Status status) {
        return orderRepository.findById(id)
                .map(o -> {
                    o.setStatus(status);
                    return orderRepository.save(o);
                }).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
