package com.project.welltrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationForm {

	@GetMapping("reservation")
	public String reservation() {
		return "URL :  reservation";
	}
}
