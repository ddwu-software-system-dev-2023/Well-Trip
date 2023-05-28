package com.project.welltrip.controller;

import com.project.welltrip.dto.DmDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@SessionAttributes("userSession")
public class DmController {



    @GetMapping("/dmList")
    public ModelAndView dmListShow() {
        return new ModelAndView("index");
    }

    @GetMapping("/dm")
    public String dmFormShow(){
        return "dm/dmForm";
    }

    @RequestMapping("/dm")
    public ModelAndView onSubmit(
            HttpServletRequest request, Model model, HttpSession session,
            @Valid @ModelAttribute("dmDto") DmDto dmDto,
            BindingResult result) throws Exception {


        dmDto.getDm().setSender("test@naver.com");

        LocalDateTime now = LocalDateTime.now();
        now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        dmDto.getDm().setSendDate(now);

        System.out.println(dmDto.getDm().getSender());
        System.out.println(dmDto.getDm().getReceiver());
        System.out.println(dmDto.getDm().getContent());
        System.out.println(dmDto.getDm().getSendDate());

        if (result.hasErrors()) {
            return new ModelAndView("dm/dmForm", "message",
                    "⛔ Invalid email or content! ⛔");
        }

//        try {
//            if (userDto.isNewUser()) {
//                wellTrip.insertUser(userDto.getUser());
//            }
//            else {
//                wellTrip.updateUser(userDto.getUser());
//            }
//        }
//        catch (DataIntegrityViolationException ex) {
//            result.rejectValue("user.userId", "USER_ID_ALREADY_EXISTS",
//                    "User ID already exists: choose a different ID.");
//            return "user/join";
//        }

        return new ModelAndView("index");

    }
}
