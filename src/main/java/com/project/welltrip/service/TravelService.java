package com.project.welltrip.service;

import com.project.welltrip.domain.Plan;
import com.project.welltrip.domain.Travel;
import com.project.welltrip.domain.User;
import com.project.welltrip.dto.PlanCreateDto;
import com.project.welltrip.dto.TravelCreateDto;
import com.project.welltrip.dto.TravelDto;
import com.project.welltrip.repository.PlanRepository;
import com.project.welltrip.repository.TravelRepository;
import com.project.welltrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TravelService {
    private final TravelRepository travelRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    // 여행 일정 생성
    public Travel make(Long userId, TravelCreateDto travelCreateDto) {
        Optional<User> findUser = userRepository.findById(userId);
        User user = findUser.get();
        Travel travel = travelRepository.save(travelCreateDto.toEntity());
        return travel;
    }

    // 여행 일정 추가
    public Plan addPlan(Long userId, PlanCreateDto planCreateDto) {
        Optional<User> findUser = userRepository.findById(userId);
        User user = findUser.get();
        Plan plan = planRepository.save(planCreateDto.toEntity());
        return plan;
    }

    // 여행 일정 확인
    public List<TravelDto> findTravel() {
        List<Travel> travels = travelRepository.findAll();
        List<TravelDto> travelDtos = new ArrayList<>();
        for (Travel travel : travels) {
            TravelDto travelDto = TravelDto.builder().travel(travel).build();
            travelDtos.add(travelDto);
        }
        return travelDtos;
    }

}
