package com.project.welltrip.dto;

import com.project.welltrip.domain.Dm;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.io.Serializable;
@SuppressWarnings("serial")
@Getter
@Setter
public class DmDto implements Serializable {

    @Valid
    Dm dm;

    public DmDto(Dm dm) {
        this.dm = dm;
    }

    public Dm getDm() {
        return dm;
    }
}
