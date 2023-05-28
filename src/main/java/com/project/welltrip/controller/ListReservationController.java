package com.project.welltrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListReservationController {

	@GetMapping("reservation/view")
	public String view() {
		return "URL :  reservation/view";
	}
}
