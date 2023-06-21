package com.project.welltrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("userSession")
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

//    @RequestMapping("/check")
//    public String check() {
//        return "user/mail";
//    }


}
