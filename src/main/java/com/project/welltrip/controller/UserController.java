package com.project.welltrip.controller;

import com.project.welltrip.domain.User;
import com.project.welltrip.dto.UserDto;
import com.project.welltrip.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
//@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    private final MailService mailService;

    @ModelAttribute("userDto")
    public UserDto formBackingObject(HttpServletRequest request)
            throws Exception {
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        if (userSession != null) {    // edit an existing account
            return new UserDto(
                    userService.getUser(userSession.getUser().getId()));
        } else {    // create a new account
            return new UserDto();
        }
    }

    // 회원 가입 GET
    @GetMapping("/join")
    public String showJoinForm() {
        return "user/join";
    }

    // 회원 가입 & 회원 정보 수정
    @PostMapping(value={"/join","/my-page/edit"})
    public String join(
            HttpServletRequest request, Model model, HttpSession session,
            @Valid @ModelAttribute("userDto") UserDto userDto,
            BindingResult result) throws Exception {

        String url;
        UserSession userSession;
        User user = null;

        if(request.getServletPath().equals("/join"))
            url = "user/join";
        else {
            userSession = (UserSession) session.getAttribute("userSession");
            user = userSession.getUser();
            url = "user/edit";
        }


        if (result.hasErrors() || !userDto.isSamePassword(userDto.getConfirmPassword())) {
            if (!userDto.isSamePassword(userDto.getConfirmPassword()))
                model.addAttribute("mismatchPassword", "⛔ Password가 일치 하지 않습니다. ⛔");
            if (userService.getUserByEmail(userDto.getUser().getEmail()) != null)
                model.addAttribute("duplicateEmail", "⛔ 이미 사용 중인 email 입니다. ⛔");

            userDto.getUser().setPassword(null);
            userDto.setConfirmPassword(null);
            model.addAttribute("userDto", userDto);

            return url;
        }


        if (url.equals("user/join")){
            userDto.getUser().setCreatedDate(LocalDateTime.now());
            userService.insertUser(userDto);

            return "redirect:/login";
        }
        else {
            userService.updateUser(user, userDto.getUser());
            return "redirect:/";
        }
    }

    @GetMapping("/my-page/edit")
    public String showEditForm(HttpServletRequest request, HttpSession session,
                               @ModelAttribute("userDto") UserDto userDto) {

        UserSession userSession = (UserSession) session.getAttribute("userSession");
        User user = userSession.getUser();

        userDto = new UserDto(user);

        return "user/edit";
    }


    @GetMapping("/searchId")
    public String showSearchIdForm() {
        return "user/searchId";
    }

    @PostMapping("/searchId")
    public ModelAndView searchId(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        boolean check = true;

        System.out.println("check: " + request.getParameter("firstName"));

        if (request.getParameter("firstName").equals("")) {
            mv.addObject("firstNameError", "⛔ 영문 이름(First Name)은 필수 정보입니다. ⛔");
            System.out.println("check:1 " + request.getParameter("firstName"));
            check = false;
        }

        if (request.getParameter("lastName").equals("")) {
            mv.addObject("lastNameError", "⛔ 영문 성(Last Name)은 필수 정보입니다. ⛔");
            System.out.println("check:2 " + request.getParameter("firstName"));
            check = false;
        }

        if (request.getParameter("phone").equals("")) {
            mv.addObject("phoneError", "⛔ 휴대전화 번호는 필수 정보입니다. ⛔");
            System.out.println("check:3 " + request.getParameter("firstName"));
            check = false;
        } else {
            if (!request.getParameter("phone").matches("^01\\d{1}\\d{3,4}\\d{4}$")) {
                mv.addObject("phoneError2", "⛔ 형식은 ex) 010XXXXXXXX 입니다. ⛔");
                System.out.println("check:4 " + request.getParameter("firstName"));
                check = false;
            }
        }

        if (!check) {
            mv.setViewName("/user/searchId");
            return mv;
        }

        User user = userService.findByFirstNameAndLastNameAndPhone(
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("phone"));

        if (user == null) {
            return new ModelAndView("user/searchedId", "message",
                    "⛔ Invalid email! ⛔");
        }


        mv.addObject("email", user.getEmail());
        mv.setViewName("user/searchedId");
        return mv;
    }

    @GetMapping("/searchPw")
    public String showSearchPwForm() {
        return "user/searchPw";
    }

    @PostMapping("/searchPw")
    public ModelAndView searchPw(HttpServletRequest request) throws ParseException {

        ModelAndView mv = new ModelAndView();
        boolean check = true;

        System.out.println("check: " + request.getParameter("firstName"));

        if (request.getParameter("email").equals("")) {
            mv.addObject("emailError", "⛔ 이메일은 필수 정보입니다. ⛔");
            check = false;
        } else {
            if (!request.getParameter("email").matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")){
                mv.addObject("emailError2", "⛔ 이메일 형식이 아닙니다. ⛔");
                check = false;
            }
        }

        if (request.getParameter("birthDate").equals("")) {
            mv.addObject("birthDateError", "⛔ 생년월인은 필수 정보입니다. ⛔");
            check = false;
        }


        if (request.getParameter("phone").equals("")) {
            mv.addObject("phoneError", "⛔ 휴대전화 번호는 필수 정보입니다. ⛔");
            check = false;
        } else {
            if (!request.getParameter("phone").matches("^01\\d{1}\\d{3,4}\\d{4}$")) {
                mv.addObject("phoneError2", "⛔ 형식은 ex) 010XXXXXXXX 입니다. ⛔");
                System.out.println("check:4 " + request.getParameter("firstName"));
                check = false;
            }
        }

        if (!check) {
            mv.setViewName("/user/searchPw");
            return mv;
        }

        DateFormat df = new SimpleDateFormat(("yyyy-MM-dd"));
        Date birthDate = df.parse(request.getParameter("birthDate"));

        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        Date d = fm.parse(request.getParameter("birthDate"));

        String birth = request.getParameter("birthDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long l = sdf.parse(birth).getTime();
        Date date = new Date(l);

        User user = userService.findByEmailAndPhoneAndBirthDate(
                request.getParameter("email"),
                request.getParameter("phone"),
                date);

        mv.addObject("email", user.getEmail());

        mv.addObject("password", "1212121212");
        mv.setViewName("user/searchedPw");
        return mv;
    }


    @RequestMapping("/join/existIdCheck")
    public ModelAndView existIdCheck(@ModelAttribute("userDto") UserDto userDto) throws Exception {

        String email = userDto.getUser().getEmail();

        ModelAndView mv = new ModelAndView();
        if (userService.getUserByEmail(email) != null)
            mv.addObject("message", "⛔ Invalid email! ⛔");
        else
            mv.addObject("message", "✔ Valid email! ✔");

        System.out.println("userDto 넘어온 email " + userDto.getUser().getEmail());

        mv.addObject("userDto", userDto);
        mv.setViewName("redirect:/join");
        return mv;
    }

    @GetMapping("/delete")
    public String deleteMem(HttpSession session) throws Exception {
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        User user = userSession.getUser();

        userService.deleteUser(user);
        session.removeAttribute("userSession");
        session.invalidate();

        return "redirect:/";
    }

//    @PostMapping("/mailConfirm")
//    @ResponseBody
//    public String mailConfirm(@RequestParam String email) throws Exception {
//        String code = mailService.sendSimpleMessage(email);
//        log.info("인증코드 : " + code);
//        return code;
//    }
}
