package com.project.welltrip.controller;

import com.project.welltrip.domain.Dm;
import com.project.welltrip.domain.Follow;
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
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@SessionAttributes("userSession")
public class DmController {

    private final DmService dmService;
    private final UserService userService;

    @RequestMapping(value={"/dm/dmList", "/dm/receivedDmList", "/my-page/dm/dmList",
    "/my-page/dm/receivedDmList"})
    public ModelAndView dmListShow(HttpServletRequest request) {

        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

        ModelAndView mv = new ModelAndView();

        if(request.getServletPath().equals("/dm/dmList") || request.getServletPath().equals("/my-page/dm/dmList")) {
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

    @GetMapping(value={"/dm/dmForm", "/my-page/dm/dmForm"})
    public ModelAndView dmFormShow(@Valid @ModelAttribute("dmDto") DmDto dmDto, BindingResult result){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/dm/dmForm");
        mv.addObject("dmDto");
        return mv;
    }

    //    @RequestMapping({"/dm/dmForm", "/dm/dmForm/{user_Id}"})
    @PostMapping(value={"/dm/dmForm", "/my-page/dm/dmForm"})
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
            mv.setViewName("/dm/dmForm");
            mv.addObject("message","⛔ Invalid content! ⛔");

        } else{
            mv.setViewName("redirect:/dm/dmList");
            dmDto.getDm().setSendDate(LocalDateTime.now());
            dmDto.getDm().setUser(user);
            dmService.insertDm(dmDto.getDm());

        }

        return mv;

    }


    @GetMapping("/dm/detailDm/{dmId}")
    public ModelAndView dmDetails (HttpServletRequest request,
                                   @Valid @ModelAttribute("dmDto") DmDto dmDto, BindingResult result,
                                   @PathVariable Long dmId) throws Exception {

        ModelAndView mv = new ModelAndView();

        Dm dm = dmService.getDm(dmId);
        dmDto.setDm(dm);

        mv.addObject("dmDto", dmDto);
        mv.setViewName("dm/dmFormDetail");

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

    @RequestMapping(value={"/dm/dmList/delete/{dmId}", "/dm/receivedDmList/delete/{dmId}"})
    public String deleteDm(HttpServletRequest request, @PathVariable long dmId){

        dmService.deleteDm(dmId);

        if(request.getServletPath().equals("/dm/dmList/delete/"+dmId))
            return "redirect:/dm/dmList/";
        else
            return "redirect:/dm/receivedDmList";
    }
}
