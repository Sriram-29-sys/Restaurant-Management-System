package com.rms.service;

import com.rms.entity.Order;
import com.rms.entity.TableEntity;
import java.util.List;

public interface OrderService {
	Order placeOrder(Order order);

	List<Order> getOrdersByTable(TableEntity table);

	List<Order> getOrdersByStatus(Order.Status status);

	Order updateOrderStatus(Long id, Order.Status status);
}
