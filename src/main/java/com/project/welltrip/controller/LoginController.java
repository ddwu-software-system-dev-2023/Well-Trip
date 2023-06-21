package com.project.welltrip.controller;

import com.project.welltrip.domain.User;
import com.project.welltrip.service.UserService;
import com.project.welltrip.service.WellTripFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * written by jiruen
 * date: 23.06.08
 */

@Controller
@RequiredArgsConstructor
@SessionAttributes("userSession")
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginShow() {
        return "user/login";
    }

    @PostMapping("/login")
    public ModelAndView handleRequest(HttpServletRequest request,
                                      @RequestParam("email") String email,
                                      @RequestParam("password") String password,
                                      @RequestParam(value="forwardAction", required=false) String forwardAction,
                                      Model model) throws Exception {

        User user = userService.getUserByEmailAndPassword(email, password);

//        System.out.println("forward"+forwardAction);
//        System.out.println("user : "+user);

        if (user == null) {
            return new ModelAndView("user/login", "message",
                    "⛔ Invalid email or password! ⛔");
        }
        else {
            UserSession userSession = new UserSession(user);
            System.out.println(userSession);

            model.addAttribute("userSession", userSession);

            if (forwardAction != null)
                return new ModelAndView("redirect:" + forwardAction);
            else
                return new ModelAndView("redirect:/");
        }
    }
}
