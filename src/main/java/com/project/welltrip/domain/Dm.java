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
        name = "DM_SEQ_GENERATOR",
        sequenceName = "DM_SEQ", // 시퀸스 명
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dm")
public class Dm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_SEQ_GENERATOR")
    private Long id; // dm Id

//    private String sender; // 보내는 사람 -> user_info의 외래키 fk

    @NotEmpty
    private String receiver; // 받는 사람

    @NotEmpty
    private String content; // 내용

    @NotEmpty
    private LocalDateTime sendDate; // 수신한 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;

}
