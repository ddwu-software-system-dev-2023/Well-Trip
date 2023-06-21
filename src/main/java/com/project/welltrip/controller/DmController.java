package com.project.welltrip.controller;

import com.project.welltrip.domain.Dm;
import com.project.welltrip.domain.User;
import com.project.welltrip.dto.DmDto;
import com.project.welltrip.repository.DmRepository;
import com.project.welltrip.service.DmService;
import com.project.welltrip.service.UserService;
import com.project.welltrip.service.WellTripFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes("userSession")
public class DmController {

    private final DmService dmService;
    private final UserService userService;

    @RequestMapping(value={"/dm/dmList", "/dm/receivedDmList"})
    public ModelAndView dmListShow(HttpServletRequest request) {

        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

        ModelAndView mv = new ModelAndView();

        if(request.getServletPath().equals("/dm/dmList")) {
            mv.setViewName("/dm/dmList");
            List<Dm> dmList = dmService.getDmByReceiver(user.getEmail());
            mv.addObject("dmList", dmList);
        }
        else {
            mv.setViewName("/dm/receivedDmList");
            List<Dm> receivedDmList = dmService.getDmByUserID(user.getId());
            mv.addObject("receivedDmList", receivedDmList);
        }
        return mv;
    }

    @GetMapping("/dm/dmForm")
    public String dmFormShow(){
        return "dm/dmForm";
    }

    //    @RequestMapping({"/dm/dmForm", "/dm/dmForm/{user_Id}"})
    @RequestMapping({"/dm/dmForm"})
    public ModelAndView onSubmit(
            HttpServletRequest request, Model model,
            @Valid @ModelAttribute("dmDto") DmDto dmDto, BindingResult result) throws Exception {


        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

//        LocalDateTime now = LocalDateTime.now();
//        now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
//        dmDto.getDm().setSendDate(now);

        ModelAndView mv = new ModelAndView();

        if (result.hasErrors()) {
            mv.setViewName("/dm/dmForm");
            model.addAttribute("dmDto", dmDto);
            return mv;
        }

        if(userService.getUserByEmail(dmDto.getDm().getReceiver()) == null || user.getEmail().equals(dmDto.getDm().getReceiver())){
            mv.addObject("message","⛔ Invalid content! ⛔");
            mv.setViewName("/dm/dmForm");
        } else{
            dmDto.getDm().setSendDate(LocalDateTime.now());
            dmDto.getDm().setUser(user);
            dmService.insertDm(dmDto.getDm());
            mv.setViewName("redirect:/dm/dmList");
        }

        return mv;

    }


    @GetMapping("/dm/{dmId}")
    public ModelAndView dmDetails (HttpServletRequest request, @PathVariable Long dmId) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("dmId", "test@test.com");
        mv.setViewName("index");

        return mv;
    }

    @GetMapping("/dm/{dmId}/send")
    public ModelAndView dmDetails2 (HttpServletRequest request,
                                    @PathVariable String dmId) throws Exception {

        ModelAndView mv = new ModelAndView();
        System.out.println("dmId: "+dmId);


        mv.addObject("test", "test@test.com");

        mv.setViewName("dm/dmForm");
        return mv;
    }
}
