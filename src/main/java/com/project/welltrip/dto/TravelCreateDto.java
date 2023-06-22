package com.project.welltrip.dto;

import com.project.welltrip.domain.DisclosureScope;
import com.project.welltrip.domain.Travel;
import com.project.welltrip.domain.Traveler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCreateDto {

    @NotNull(message = "날짜를 입력해야 합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "날짜를 입력해야 합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotBlank(message = "여행할 곳을 입력해야 합니다.")
    private String country;

    private DisclosureScope scope;

    public Travel toEntity() {
        return Travel.builder()
                .startDate(startDate)
                .endDate(endDate)
                .country(country)
                .scope(scope)
                .build();
    }
}
