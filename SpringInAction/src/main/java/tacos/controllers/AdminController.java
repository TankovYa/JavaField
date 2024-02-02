package tacos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.OrderAdminService;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private OrderAdminService adminService;
	
	public AdminController(OrderAdminService adminService) {
		this.adminService = adminService;
	}
	
	@PostMapping("/deleteOrders")
	public String deleteAllOrders() {
		adminService.deleteAllOrders();
		return "redirect:/admin";
	}
}
