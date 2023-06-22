package com.project.welltrip.dto;

import com.project.welltrip.domain.DisclosureScope;
import com.project.welltrip.domain.Plan;
import com.project.welltrip.domain.Travel;
import com.project.welltrip.domain.Traveler;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TravelDto {

    private Long id;
    private List<Traveler> travelers;

    private LocalDate startDate;
    private LocalDate endDate;
    private String country;

    private List<Plan> plans;

    private DisclosureScope scope;

    public Travel toEntity() {
        return Travel.builder()
                .travelers(travelers)
                .startDate(startDate)
                .endDate(endDate)
                .country(country)
                .plans(plans)
                .scope(scope)
                .build();
    }

    @Builder
    public TravelDto(Travel travel) {
        this.id = travel.getId();
        this.travelers = travel.getTravelers();
        this.startDate = travel.getStartDate();
        this.endDate = travel.getEndDate();
        this.country = travel.getCountry();
        this.plans = travel.getPlans();
        this.scope = travel.getScope();
    }
}
