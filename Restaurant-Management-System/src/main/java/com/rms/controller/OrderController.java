package com.rms.controller;

import com.rms.entity.Order;
import com.rms.entity.TableEntity;
import com.rms.service.OrderService;
import com.rms.service.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;
	private final TableService tableService;

	public OrderController(OrderService orderService, TableService tableService) {
		this.orderService = orderService;
		this.tableService = tableService;
	}

	// Show all orders
	@GetMapping
	public String getAllOrders(Model model) {
		List<Order> pendingOrders = orderService.getOrdersByStatus(Order.Status.PENDING);
		List<Order> preparingOrders = orderService.getOrdersByStatus(Order.Status.PREPARING);
		List<Order> servedOrders = orderService.getOrdersByStatus(Order.Status.SERVED);

		model.addAttribute("pendingOrders", pendingOrders);
		model.addAttribute("preparingOrders", preparingOrders);
		model.addAttribute("servedOrders", servedOrders);

		return "orders"; // JSP page: orders.jsp
	}

	// Place order for a table
	@PostMapping("/place/{tableId}")
	public String placeOrder(@PathVariable Long tableId, @ModelAttribute Order order) {
		TableEntity table = tableService.findByTableNumber(Math.toIntExact(tableId))
				.orElseThrow(() -> new RuntimeException("Table not found"));
		order.setTable(table);
		orderService.placeOrder(order);
		return "redirect:/orders";
	}

	// Update order status
	@PostMapping("/update/{id}")
	public String updateOrderStatus(@PathVariable Long id, @RequestParam("status") Order.Status status) {
		orderService.updateOrderStatus(id, status);
		return "redirect:/orders";
	}
}
