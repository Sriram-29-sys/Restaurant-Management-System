package com.rms.controller;

import com.rms.entity.Order;
import com.rms.entity.OrderItem;
import com.rms.service.OrderItemService;
import com.rms.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order-items")
public class OrderItemController {

	private final OrderItemService orderItemService;
	private final OrderService orderService;

	public OrderItemController(OrderItemService orderItemService, OrderService orderService) {
		this.orderItemService = orderItemService;
		this.orderService = orderService;
	}

	// Show items for an order
	@GetMapping("/{orderId}")
	public String getOrderItems(@PathVariable Long orderId, Model model) {
		Order order = orderService.getOrdersByStatus(Order.Status.PENDING).stream()
				.filter(o -> o.getId().equals(orderId)).findFirst()
				.orElseThrow(() -> new RuntimeException("Order not found"));
		List<OrderItem> items = orderItemService.getOrderItemsByOrder(order);

		model.addAttribute("order", order);
		model.addAttribute("items", items);

		return "order-items"; // JSP page: order-items.jsp
	}

	// Add item to order
	@PostMapping("/add/{orderId}")
	public String addOrderItem(@PathVariable Long orderId, @ModelAttribute OrderItem orderItem) {
		Order order = orderService.getOrdersByStatus(Order.Status.PENDING).stream()
				.filter(o -> o.getId().equals(orderId)).findFirst()
				.orElseThrow(() -> new RuntimeException("Order not found"));
		orderItem.setOrder(order);
		orderItemService.addOrderItem(orderItem);
		return "redirect:/order-items/" + orderId;
	}
}
