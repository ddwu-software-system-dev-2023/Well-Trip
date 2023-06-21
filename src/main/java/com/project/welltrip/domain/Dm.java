package com.project.welltrip.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Entity
@SequenceGenerator(
        name = "DM_SEQ_GENERATOR",
        sequenceName = "DM_SEQ", // 시퀸스 명
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Table(name = "dm")
public class Dm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_SEQ_GENERATOR")
    private Long dmId; // dm Id

//    private String sender; // 보내는 사람 -> user_info의 외래키 fk

    @NotEmpty(message = "⛔ 받는 사람을 입력해주세요. ⛔")
    private String receiver; // 받는 사람

    @NotEmpty(message = "⛔ 내용을 입력해주세요. ⛔")
    private String content; // 내용

    private LocalDateTime sendDate; // 수신한 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;

    public Dm() {}

    public Dm(Long dmId) {
        this.dmId = dmId;
    }
}
