package com.project.welltrip.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;
/**
 * written by nayeon
 * date: 23.05.28
 * content에 들어가는 값 수정 필요
 */
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Place extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "place_id")
    private Long id;

    private String type;

    private String name;
    private String content; // 관련 내용 (영업 시간 등)

    @OneToMany(mappedBy = "place")
    private List<Post> reviews = new ArrayList<>();

    private Long latitude;
    private Long longitude;
}
