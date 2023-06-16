package com.project.welltrip.domain;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Scrap {

    @Id @GeneratedValue
    @Column(name = "scrap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
