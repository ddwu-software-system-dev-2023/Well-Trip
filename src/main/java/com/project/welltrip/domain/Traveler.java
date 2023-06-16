package com.project.welltrip.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

/**
 * written by nayeon
 * date: 23.05.28
 */

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Traveler {

    @Id @GeneratedValue
    @Column(name = "traveler_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_id")
    private Travel travel;
}
