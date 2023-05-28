package com.project.welltrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchAirplaneController {

	@GetMapping("plane/search")
	public String search() {
		return "URL :  plane/search";
	}
	
}
