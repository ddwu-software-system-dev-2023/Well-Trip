package com.project.welltrip.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ViewAirplaneController {
	@GetMapping("/plane/info")
	public String info() {
		return "URL :  plane/info";
	}
}
