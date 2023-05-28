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
@Table(name = "follow")
public class Follow {

    @Id
    private Long followId; // follow Id

    private String follower; // 로그인한 사용자

    @NotEmpty
    private String followee; // 친구 신청을 당하는 사용자

    @NotEmpty
    private LocalDateTime followDate;

    public Follow() {
    }

    public Follow(Long followId) {
        this.followId = followId;
    }
}
