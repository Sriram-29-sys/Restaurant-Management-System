package com.rms.service;

import com.rms.entity.MenuItem;
import java.util.List;

public interface MenuService {
	MenuItem addMenuItem(MenuItem menuItem);

	MenuItem updateMenuItem(Long id, MenuItem menuItem);

	void deleteMenuItem(Long id);

	List<MenuItem> getAllMenuItems();

	List<MenuItem> getAvailableMenuItems();

	List<MenuItem> getMenuItemsByCategory(String category);
}
