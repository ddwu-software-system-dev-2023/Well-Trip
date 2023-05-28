package com.project.welltrip.controller;

import com.project.welltrip.dto.UserDto;
import com.project.welltrip.service.UserDtoValidator;
import com.project.welltrip.service.WellTripFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/join")
public class UserController {

    @Autowired
    private WellTripFacade wellTrip;

    public void setWellTrip(WellTripFacade wellTrip) {
        this.wellTrip = wellTrip;
    }

    @Autowired
    private UserDtoValidator validator;
    public void setValidator(UserDtoValidator validator) {
        this.validator = validator;
    }

//    @ModelAttribute("userDto")
//    public UserDto formBackingObject(HttpServletRequest request)
//            throws Exception {
//        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
//        if (userSession != null) {	// edit an existing account
//            return new UserDto(
//                    wellTrip.getUser(userSession.getUser().getEmail()));
//        }
//        else {	// create a new account
//            return new UserDto();
//        }
//    }

    @RequestMapping(method = RequestMethod.GET)
    public String showForm() {
        return "user/join";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(
            HttpServletRequest request, Model model, HttpSession session,
            @Valid @ModelAttribute("userDto") UserDto userDto,
            BindingResult result) throws Exception {

        validator.validate(userDto, result);
        System.out.println(userDto.isNewUser());

        if (result.hasErrors()) {
            System.out.println(result);
            return "user/join";
        }

        try {
            if (userDto.isNewUser()) {
                System.out.println(userDto.getUser());
                System.out.println(userDto.getUser().getId());

                userDto.getUser().setCreatedDate(LocalDateTime.now());

                wellTrip.insertUser(userDto.getUser());
            }
            else {
                wellTrip.updateUser(userDto.getUser());
            }
        }
        catch (DataIntegrityViolationException ex) {
            result.rejectValue("user.email", "USER_ID_ALREADY_EXISTS",
                    "User ID already exists: choose a different ID.");
            return "user/join";
        }


        System.out.println(userDto.getUser().getEmail());

        UserSession userSession = new UserSession(
                wellTrip.getUser(userDto.getUser().getEmail()));
        session.setAttribute("userSession", userSession);

        return "user/joined";
    }


}
