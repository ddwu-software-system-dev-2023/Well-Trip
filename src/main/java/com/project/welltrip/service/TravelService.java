package com.project.welltrip.service;

import com.project.welltrip.domain.*;
import com.project.welltrip.dto.PlanCreateDto;
import com.project.welltrip.dto.TravelCreateDto;
import com.project.welltrip.dto.TravelDto;
import com.project.welltrip.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * written by nayeon
 * date: 23.06.16
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TravelService {
    private final TravelRepository travelRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final TravelerRepository travelerRepository;
    private final PlaceRepository placeRepository;

    // 여행 일정 생성
    public Travel createTravel(Long userId, TravelCreateDto travelCreateDto) {
        Optional<User> findUser = userRepository.findById(userId);
        User user = findUser.get();

        Travel travel = travelRepository.save(travelCreateDto.toEntity());
        Traveler traveler = new Traveler(1L, user, travel);
        Traveler saved = travelerRepository.save(traveler);
        
        return travel;
    }

    // 여행 일정 추가
    public Plan addPlan(Long userId, PlanCreateDto planCreateDto) {
        Travel travel = travelRepository.findById(planCreateDto.getTravelId()).get();
        planCreateDto.setTravel(travel);
        Place place = placeRepository.findById(planCreateDto.getPlaceId()).get();
        planCreateDto.setPlace(place);

        Optional<User> findUser = userRepository.findById(userId);
        User user = findUser.get();

        Plan plan = planRepository.save(planCreateDto.toEntity());
        return plan;
    }

    // plan 수정
    public Plan updatePlan(Long planId, PlanCreateDto planDto) {
        Place place = placeRepository.findById(planDto.getPlaceId()).get();
        planDto.setPlace(place);

        Plan plan = planRepository.findById(planId).get();
        plan.updatePlan(planDto);
        return plan;
    }

    // plan 삭제
    public Long deletePlan(Long userId, Long planId) {
        planRepository.deleteById(planId);
        return planId;
    }

    // 여행자 추가
    public Traveler addTraveler(Long userId, Long travelId) {
        User user = userRepository.findById(userId).get();
        Travel travel = travelRepository.findById(travelId).get();
        Traveler traveler = Traveler.builder()
                .user(user)
                .travel(travel)
                .build();
        Traveler saved = travelerRepository.save(traveler);
        return saved;
    }

    // 일정에서 나가기
    public Long deleteTraveler(Long userId, Long travelId) {
        Traveler traveler = travelerRepository.findTraveler(userId, travelId).get();
        travelerRepository.deleteById(traveler.getId());
        return travelId;
    }

    // 여행 수정 (여행날짜, 여행 도시 등등)
    // 필요 시, 추후에 추가 예정

    // 여행 일정 확인
    @Transactional(readOnly = true)
    public List<TravelDto> findTravels() {
        List<Travel> travels = travelRepository.findAll();
        List<TravelDto> travelDtos = new ArrayList<>();
        for (Travel travel : travels) {
            TravelDto travelDto = TravelDto.builder().travel(travel).build();
            travelDtos.add(travelDto);
        }
        return travelDtos;
    }

    // 내가 소속된 여행 일정 확인
    @Transactional(readOnly = true)
    public List<TravelDto> getMyTravel(Long userId) {
        List<Travel> travels = travelRepository.findAll();
        List<TravelDto> travelDtos = new ArrayList<>();
        for (Travel travel : travels) {
            for (Traveler traveler : travel.getTravelers()) {
                if (Objects.equals(traveler.getUser().getId(), userId)) {
                    TravelDto travelDto = TravelDto.builder().travel(travel).build();
                    travelDtos.add(travelDto);
                }
            }
        }
        return travelDtos;
    }


    // 여행 일정 상세보기
    @Transactional(readOnly = true)
    public TravelDto findOne(Long travelId) {
        Travel travel = travelRepository.findById(travelId).get();
        TravelDto travelDto = TravelDto.builder().travel(travel).build();
        return travelDto;
    }

}
