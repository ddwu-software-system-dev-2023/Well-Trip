package com.project.welltrip.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
public class Post extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo writer;

    private String title;
    private String content;

    @OneToMany(mappedBy = "post")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_id")
    private Travel travel;
}
