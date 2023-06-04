package com.project.welltrip.dto;

import com.project.welltrip.domain.User;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * written by jiruen
 * date: 23.06.05
 */

@SuppressWarnings("serial")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    //@Valid 해줘야 돌아감!
    @Valid
    private User user;

    @NotEmpty
    private String confirmPassword;

    public User toEntity() {
        User build = User.builder()
                .id(user.getId())
                .birthDate(user.getBirthDate())
                .createdDate(user.getCreatedDate())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .dms(user.getDms())
                .follows(user.getFollows())
                .build();
        return build;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public boolean isSamePassword() {
        return user.getPassword().equals(confirmPassword);
    }

}
