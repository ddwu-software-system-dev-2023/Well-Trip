package com.project.welltrip.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@SequenceGenerator(
        name = "FOLLOW_SEQ_GENERATOR",
        sequenceName = "FOLLOW_SEQ", // 시퀸스 명
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOLLOW_SEQ_GENERATOR")
    private Long followId; // follow Id

//    private String following; // 로그인한 사용자 -> user_info의 외래키 fk

    @NotNull
    private Long follower; // 친구 신청을 당하는 사용자

    @NotNull
    private LocalDateTime followDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;

    public Follow() {
    }

    public Follow(Long followId) {
        this.followId = followId;
    }
}