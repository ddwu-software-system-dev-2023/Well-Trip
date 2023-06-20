package com.project.welltrip.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.welltrip.domain.Product;
import com.project.welltrip.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService prdService;
	
	/* productHome */
	@GetMapping("")
	public String productHome() {
		return "product/productHome";
	}
	
	/* product */
	
	@GetMapping("/product")
	public String productSearch(Model model) {
		
		List<Product> prd = prdService.doSelectAll();
		model.addAttribute("products", prd);
		
		return "product/product";
	}
	
	@PostMapping("/product")
	public String product(HttpServletRequest httpServletRequest, Model model) {		
		// TODOS : HttpServletRequest, RequestParam, PathVariable 중 어떤 걸로 통일?? (회의 필요)
		
		
		String departure = httpServletRequest.getParameter("departure");
		String arrival = httpServletRequest.getParameter("arrival");
		
		List<Product> prd = prdService.doSelectByDepartureAndArrival(departure, arrival);		
		model.addAttribute("products", prd);
		
		return "product/product";
	}
	
	/* Checkout */
//	@GetMapping("/checkout")
//	public String checkout() {
//		return "product/checkout";
//	}
	
	@PostMapping("/checkout")
	public String checkout(HttpServletRequest httpServletRequest, Model model) {
		
		// TODO : check 된 여행상품을 받아와야 한다.
		
		return "product/checkout";		
	}
	
	/* Confirm */
//	@GetMapping("/confirm")
//	public String confirm() {
//		return "product/confirm";
//	}
	
	/* Confirm */
	@PostMapping("/confirm")
	public String confirm() {
		return "product/confirm";
	}
}