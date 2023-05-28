package com.project.welltrip.controller;

import com.project.welltrip.domain.User;
import com.project.welltrip.service.WellTripFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
@SessionAttributes("userSession")
public class LoginOutController {

    private WellTripFacade wellTrip;
    @Autowired
    public void setWellTrip(WellTripFacade wellTrip) {
        this.wellTrip = wellTrip;
    }

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
//        User user = wellTrip.getUser(userId, password);
        User user = new User(email, password);
        System.out.println("userId:"+email);
        if (user.getEmail() != null && user.getPassword() != null &&
                user.getEmail().equals("test@naver.com") && user.getPassword().equals("testtest"))
            return new ModelAndView("index");
        else {
            return new ModelAndView("user/login", "message",
                    "⛔ Invalid email or password! ⛔");
        }

//        if (user == null) {
//            return new ModelAndView("user/Error", "message",
//                    "Invalid username or password.  Signon failed.");
//            return new ModelAndView("user/login");
//        }
//        else {
//            UserSession userSession = new UserSession(user);
//            model.addAttribute("userSession", userSession);
//            if (forwardAction != null)
//                return new ModelAndView("redirect:" + forwardAction);
//            else
//                return new ModelAndView("index");
//        }
    }
    @RequestMapping("/logout")
    public String handleRequest(HttpSession session) throws Exception {
        session.removeAttribute("userSession");
        session.invalidate();
        return "index";
    }
}
