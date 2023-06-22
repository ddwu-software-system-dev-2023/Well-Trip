package com.project.welltrip.dto;

import com.project.welltrip.domain.Place;
import com.project.welltrip.domain.Plan;
import com.project.welltrip.domain.Travel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanCreateDto {

    private Long travelId;

    private Long placeId;

    private Long planId;

    private Place place;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;

    private String memo;

    private Travel travel;

    public Plan toEntity() {
        return Plan.builder()
                .place(place)
                .startTime(startTime)
                .endTime(endTime)
                .memo(memo)
                .travel(travel)
                .build();
    }
}
