package com.project.welltrip.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@Entity
@Table(name = "dm")
public class Dm {

    @Id
    private Long dmId; // dm Id

    private String sender; // 보내는 사람

    @NotEmpty
    private String receiver; // 받는 사람

    @NotEmpty
    private String content; // 내용

    private LocalDateTime sendDate; // 수신한 날짜

    public Dm() {}

    public Dm(Long dmId) {
        this.dmId = dmId;
    }
}
