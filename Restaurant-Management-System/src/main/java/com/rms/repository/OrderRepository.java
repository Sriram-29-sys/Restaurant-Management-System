package com.rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rms.entity.Order;
import com.rms.entity.TableEntity;

public interface OrderRepository extends JpaRepository<Order, Long> {
	// Find all orders for a table
	List<Order> findByTable(TableEntity table);

	// Find by status
	List<Order> findByStatus(Order.Status status);
}
