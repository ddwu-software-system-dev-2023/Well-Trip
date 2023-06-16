package com.project.welltrip.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * written by jiruen
 * date: 23.06.05
 */

@Builder
@Getter
@Entity
@SequenceGenerator(
        name = "FOLLOW_SEQ_GENERATOR",
        sequenceName = "FOLLOW_SEQ", // 시퀸스 명
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOLLOW_SEQ_GENERATOR")
    private Long id; // follow Id

//    private String follower; // 로그인한 사용자 -> user_info의 외래키 fk

    @NotEmpty
    private String followee; // 친구 신청을 당하는 사용자

    @NotEmpty
    private LocalDateTime followDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;

}
