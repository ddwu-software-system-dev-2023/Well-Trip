package com.project.welltrip.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;
/**
 * written by nayeon
 * date: 23.05.28
 */
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Travel extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "travel_id")
    private Long id;

    @OneToMany(mappedBy = "travel")
    private List<Traveler> travelers = new ArrayList<>();

    private LocalDate startDate;
    private LocalDate endDate;
    private String country;

    @OneToMany(mappedBy = "travel")
    private List<Plan> plans = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DisclosureScope scope;
}
