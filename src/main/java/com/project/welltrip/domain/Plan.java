package com.project.welltrip.domain;

import com.project.welltrip.dto.PlanCreateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;
/**
 * written by nayeon
 * date: 23.05.28
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Builder
public class Plan {

    @Id @GeneratedValue
    @Column(name = "plan_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_id")
    private Travel travel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String memo;

    public void updatePlan(PlanCreateDto planDto) {
        this.place = planDto.getPlace();
        this.startTime = planDto.getStartTime();
        this.endTime = planDto.getEndTime();
        this.memo = planDto.getMemo();
    }
}
