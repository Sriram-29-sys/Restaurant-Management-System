package com.rms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rms.entity.MenuItem;
import com.rms.repository.MenuRepository;
import com.rms.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	private final MenuRepository menuRepository;

	public MenuServiceImpl(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Override
	public MenuItem addMenuItem(MenuItem menuItem) {
		return menuRepository.save(menuItem);
	}

	@Override
	public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
		return menuRepository.findById(id).map(existing -> {
			existing.setName(menuItem.getName());
			existing.setCategory(menuItem.getCategory());
			existing.setPrice(menuItem.getPrice());
			existing.setAvailable(menuItem.isAvailable());
			return menuRepository.save(existing);
		}).orElseThrow(() -> new RuntimeException("Menu item not found"));
	}

	@Override
	public void deleteMenuItem(Long id) {
		menuRepository.deleteById(id);
	}

	@Override
	public List<MenuItem> getAllMenuItems() {
		return menuRepository.findAll();
	}

	@Override
	public List<MenuItem> getAvailableMenuItems() {
		return menuRepository.findByAvailableTrue();
	}

	@Override
	public List<MenuItem> getMenuItemsByCategory(String category) {
		return menuRepository.findByCategory(category);
	}
}
