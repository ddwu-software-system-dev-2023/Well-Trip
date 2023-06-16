package com.project.welltrip.dto;

import com.project.welltrip.domain.DisclosureScope;
import com.project.welltrip.domain.Plan;
import com.project.welltrip.domain.Travel;
import com.project.welltrip.domain.Traveler;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TravelCreateDto {

    @NotNull(message = "여행자를 선택해야 합니다.")
    private List<Traveler> travelers;

    @NotNull(message = "날짜를 입력해야 합니다.")
    private LocalDate startDate;

    @NotNull(message = "날짜를 입력해야 합니다.")
    private LocalDate endDate;

    @NotBlank(message = "여행할 곳을 입력해야 합니다.")
    private String country;

    private DisclosureScope scope;

    public Travel toEntity() {
        return Travel.builder()
                .travelers(travelers)
                .startDate(startDate)
                .endDate(endDate)
                .country(country)
                .scope(scope)
                .build();
    }
}
