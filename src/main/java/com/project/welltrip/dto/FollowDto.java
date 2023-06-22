package com.project.welltrip.dto;

import com.project.welltrip.domain.Follow;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.io.Serializable;

@SuppressWarnings("serial")
@Getter
@Setter
public class FollowDto implements Serializable {

    @Valid
    Follow follow;

    public FollowDto(Follow follow) {
        this.follow = follow;
    }

    public Follow getFollow() {
        return follow;
    }
}
