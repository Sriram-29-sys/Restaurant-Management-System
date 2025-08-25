package com.rms.controller;

import com.rms.entity.TableEntity;
import com.rms.service.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tables")
public class TableController {

	private final TableService tableService;

	public TableController(TableService tableService) {
		this.tableService = tableService;
	}

	// Show all tables
	@GetMapping
	public String getAllTables(Model model) {
		List<TableEntity> tables = tableService.getAllTables();
		model.addAttribute("tables", tables);
		return "tables"; // JSP page: tables.jsp
	}

	// Add new table
	@PostMapping("/add")
	public String addTable(@ModelAttribute TableEntity table) {
		tableService.addTable(table);
		return "redirect:/tables";
	}

	// Update table status
	@PostMapping("/update/{id}")
	public String updateTableStatus(@PathVariable Long id, @RequestParam("status") TableEntity.Status status) {
		tableService.updateTableStatus(id, status);
		return "redirect:/tables";
	}
}
