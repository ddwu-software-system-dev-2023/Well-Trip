package com.project.welltrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewAirplaneController {
	
	@GetMapping("/plane/info")
	public String info() {
		return "URL :  plane/info";
	}
	
}
