package com.project.welltrip.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
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

    private String follower; // 로그인한 사용자

    @NotEmpty
    private String followee; // 친구 신청을 당하는 사용자

    @NotEmpty
    private LocalDateTime followDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public Follow() {
    }

    public Follow(Long followId) {
        this.followId = followId;
    }
}
