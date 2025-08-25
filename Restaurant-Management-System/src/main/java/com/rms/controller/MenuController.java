package com.rms.controller;

import com.rms.entity.MenuItem;
import com.rms.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private final MenuService menuService;

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	// Show all menu items
	@GetMapping
	public String getAllMenuItems(Model model) {
		List<MenuItem> items = menuService.getAllMenuItems();
		model.addAttribute("menuItems", items);
		return "menu"; // JSP page: menu.jsp
	}

	// Add a menu item
	@PostMapping("/add")
	public String addMenuItem(@ModelAttribute MenuItem menuItem) {
		menuService.addMenuItem(menuItem);
		return "redirect:/menu";
	}

	// Update menu item
	@PostMapping("/update/{id}")
	public String updateMenuItem(@PathVariable Long id, @ModelAttribute MenuItem menuItem) {
		menuService.updateMenuItem(id, menuItem);
		return "redirect:/menu";
	}

	// Delete menu item
	@GetMapping("/delete/{id}")
	public String deleteMenuItem(@PathVariable Long id) {
		menuService.deleteMenuItem(id);
		return "redirect:/menu";
	}
}
