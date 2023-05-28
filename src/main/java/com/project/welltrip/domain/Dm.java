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
        name = "DM_SEQ_GENERATOR",
        sequenceName = "DM_SEQ", // 시퀸스 명
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Table(name = "dm")
public class Dm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_SEQ_GENERATOR")
    private Long dmId; // dm Id

    private String sender; // 보내는 사람

    @NotEmpty
    private String receiver; // 받는 사람

    @NotEmpty
    private String content; // 내용

    private LocalDateTime sendDate; // 수신한 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public Dm() {}

    public Dm(Long dmId) {
        this.dmId = dmId;
    }
}
