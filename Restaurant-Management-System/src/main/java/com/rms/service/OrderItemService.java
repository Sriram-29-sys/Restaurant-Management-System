package com.rms.service;

import com.rms.entity.Order;
import com.rms.entity.OrderItem;
import java.util.List;

public interface OrderItemService {
	OrderItem addOrderItem(OrderItem orderItem);

	List<OrderItem> getOrderItemsByOrder(Order order);
}
