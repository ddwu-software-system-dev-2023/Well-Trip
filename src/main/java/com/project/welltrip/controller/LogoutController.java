package com.project.welltrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * written by jiruen
 * date: 23.06.05
 */

@Controller
public class LogoutController {
    @RequestMapping("/logout")
    public String handleRequest(HttpSession session) throws Exception {
        session.removeAttribute("userSession");
        session.invalidate();
        return "redirect:/";
    }
}
