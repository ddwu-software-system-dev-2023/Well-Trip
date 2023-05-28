package com.project.welltrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeleteReservationController {
	
	@GetMapping("reservation/delete")
	public String delete() {
		return "URL :  reservation/delete";
	}
}
