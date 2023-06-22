package com.project.welltrip.controller;

import com.project.welltrip.domain.*;
import com.project.welltrip.dto.*;
import com.project.welltrip.repository.PlaceRepository;
import com.project.welltrip.repository.PlanRepository;
import com.project.welltrip.repository.TravelerRepository;
import com.project.welltrip.repository.UserRepository;
import com.project.welltrip.service.TravelService;
import com.project.welltrip.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * written by nayeon
 * date: 23.06.18
 */
@Controller
@RequiredArgsConstructor
public class TravelController {
    private final TravelService travelService;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final TravelerRepository travelerRepository;
    private final PlanRepository planRepository;

    // 여행 일정 리스트
    @GetMapping("/travels")
    public String view(Model model) {
        List<TravelDto> travelDtos = travelService.findTravels();
        model.addAttribute("travels", travelDtos);
        return "travel/travelList";
    }

    // 여행 일정 추가
    @GetMapping("/travels/new")
    public String createForm(Model model) {
        model.addAttribute("travelForm", new TravelCreateDto());
        return "travel/createTravelForm";
    }

    @PostMapping ("/travels/new")
    public String create(@Valid TravelCreateDto travelForm, BindingResult result) {
        if (result.hasErrors()) {
            return "travel/createTravelForm";
        }

        TravelCreateDto travelCreateDto = new TravelCreateDto(travelForm.getStartDate(), travelForm.getEndDate(), travelForm.getCountry(), travelForm.getScope());

        // TODO: userId를 현재 로그인한 유저의 아이디로 변경
        travelService.createTravel(1L, travelCreateDto);

        return "redirect:/travels";
    }

    // 여행 일정 상세보기
    @GetMapping("/travels/{travelId}")
    public String viewDetail(@PathVariable("travelId") Long travelId, Model model) {
        TravelDto travelDto = travelService.findOne(travelId);
        model.addAttribute("travel", travelDto);

        // 이미 여행자로 등록된 user는 포함하지 않음
        List<User> travelers = travelerRepository.findUser(travelId);
        model.addAttribute("travelers", travelers);

        List<User> users = userRepository.findAll();
        for (User traveler : travelers) {
            users.remove(traveler);
        }
        model.addAttribute("users", users);

        return "travel/travelDetail";
    }

    // 상세 계획 추가
    @GetMapping("/travels/{travelId}/new")
    public String createPlanForm(@PathVariable("travelId") Long travelId, Model model) {
        List<Place> places = placeRepository.findAll();
        PlanCreateDto planCreateDto = new PlanCreateDto();

        model.addAttribute("tId", travelId);
        model.addAttribute("places", places);
        model.addAttribute("planForm", planCreateDto);

        return "travel/createPlanForm";
    }

    // 상세 계획 추가 & 수정
    @PostMapping("/travels/{travelId}/new")
    public String createPlan(@PathVariable("travelId") Long travelId,
                             @Valid PlanCreateDto planForm, BindingResult result) {
        if (result.hasErrors()) {
            return "travel/createPlanForm";
        }

        PlanCreateDto planCreateDto = new PlanCreateDto(planForm.getTravelId(), planForm.getPlaceId(), planForm.getPlanId(), null, planForm.getStartTime(), planForm.getEndTime(), planForm.getMemo(), null);

        if (planForm.getPlanId() == null) { // 추가
            // TODO: userId를 현재 로그인한 유저의 아이디로 변경
            travelService.addPlan(1L, planCreateDto);
        } else { // 수정
            // TODO: userId를 현재 로그인한 유저의 아이디로 변경
            travelService.updatePlan(1L, planCreateDto.getPlanId(), planCreateDto);
        }

        return "redirect:/travels/{travelId}";
    }

    // 일정에 여행자 추가
    @PostMapping("travels/{travelId}/{userId}")
    public String addTraveler(@PathVariable("travelId") Long travelId,
                              @PathVariable("userId") Long userId) {
        Traveler traveler = travelService.addTraveler(userId, travelId);
        return "redirect:/travels/{travelId}";
    }

    // 일정 수정
    @GetMapping("/travels/{planId}/edit")
    public String editPlanForm(@PathVariable("planId") Long planId, Model model) {
        List<Place> places = placeRepository.findAll();
        Plan plan = planRepository.findById(planId).get();
        PlanCreateDto planCreateDto = new PlanCreateDto(plan.getTravel().getId(), plan.getPlace().getId(), planId, plan.getPlace(),
                plan.getStartTime(), plan.getEndTime(), plan.getMemo(), plan.getTravel());

        model.addAttribute("tId", plan.getTravel().getId());
        model.addAttribute("pId", planId);
        model.addAttribute("places", places);
        model.addAttribute("planForm", planCreateDto);

        return "travel/createPlanForm";
    }


    // 일정 삭제
    @GetMapping("travels/{travelId}/{planId}/delete")
    public String deletePlan(@PathVariable("travelId") Long travelId,
                             @PathVariable("planId") Long planId) {
        // TODO: userId를 현재 로그인한 유저의 아이디로 변경
        Long deleted = travelService.deletePlan(1L, planId);
        return "redirect:/travels/{travelId}";
    }

}
