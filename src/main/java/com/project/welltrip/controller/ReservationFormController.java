package com.project.welltrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationFormController {

	@GetMapping("reservation/update")
	public String update() {
		return "URL :  reservation/update";
	}
	
}
