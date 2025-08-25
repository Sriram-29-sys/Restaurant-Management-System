package com.rms.controller;

import com.rms.entity.User;
import com.rms.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// ---------- Auth (kept inside UserController as requested) ----------

	// Show login page
	@GetMapping("/login")
	public String showLogin(Model model) {
		return "login"; // login.jsp
	}

	// Process login (simple check using UserService.findByUsername)
	@PostMapping("/login")
	public String doLogin(@RequestParam String username, @RequestParam String password, HttpSession session,
			Model model) {

		Optional<User> optUser = userService.findByUsername(username);
		if (optUser.isPresent() && optUser.get().getPassword().equals(password)) {
			User user = optUser.get();
			session.setAttribute("loggedInUser", user);

			// Simple role-based redirect
			switch (user.getRole()) {
			case ADMIN:
				return "redirect:/menu";
			case WAITER:
				return "redirect:/orders";
			case CUSTOMER:
			default:
				return "redirect:/menu";
			}
		}

		model.addAttribute("error", "Invalid username or password");
		return "login";
	}

	// Logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	// ---------- User management (basic admin features) ----------

	// List all users (you can guard with a simple session role check)
	@GetMapping("/users")
	public String listUsers(Model model, HttpSession session) {
		if (!isAdmin(session))
			return "redirect:/login";
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "users"; // users.jsp
	}

	// Show add-user form
	@GetMapping("/users/new")
	public String showAddUserForm(Model model, HttpSession session) {
		if (!isAdmin(session))
			return "redirect:/login";
		model.addAttribute("user", new User());
		model.addAttribute("roles", User.Role.values());
		return "user-form"; // user-form.jsp
	}

	// Create user
	@PostMapping("/users")
	public String createUser(@ModelAttribute User user, HttpSession session) {
		if (!isAdmin(session))
			return "redirect:/login";
		userService.saveUser(user);
		return "redirect:/users";
	}

	// Helper: simple admin check (replace with Spring Security later)
	private boolean isAdmin(HttpSession session) {
		Object u = session.getAttribute("loggedInUser");
		if (u instanceof User) {
			return ((User) u).getRole() == User.Role.ADMIN;
		}
		return false;
	}
}
