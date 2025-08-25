package com.rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rms.entity.MenuItem;

public interface MenuRepository extends JpaRepository<MenuItem, Long> {
	// Find items by category
	List<MenuItem> findByCategory(String category);

	// Find only available items
	List<MenuItem> findByAvailableTrue();
}
