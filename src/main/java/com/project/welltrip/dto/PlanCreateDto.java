package com.project.welltrip.dto;

import com.project.welltrip.domain.Place;
import com.project.welltrip.domain.Plan;
import com.project.welltrip.domain.Travel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
public class PlanCreateDto {

    private Place place;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String memo;

    public Plan toEntity() {
        return Plan.builder()
                .place(place)
                .startTime(startTime)
                .endTime(endTime)
                .memo(memo)
                .build();
    }
}
