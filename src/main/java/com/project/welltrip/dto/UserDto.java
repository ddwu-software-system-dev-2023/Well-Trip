package com.project.welltrip.dto;

import com.project.welltrip.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@SuppressWarnings("serial")
@Getter
@Setter
public class UserDto implements Serializable {

    //@Valid 해줘야 돌아감!
    @Valid
    private User user;

    private boolean newUser;

    @NotEmpty(message = "* 비밀번호는 필수 정보입니다.")
    private String confirmPassword;


    public UserDto() {
        this.user = new User();
        this.newUser = true;
    }

    public UserDto(User user) {
        this.user = user;
        this.newUser = false;
    }

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
                .build();
        return build;
    }

    public User getUser() {
        return user;
    }


    public boolean isNewUser() {
        return newUser;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public boolean isSamePassword(String confirmPassword) {
        return user.getPassword().equals(this.confirmPassword);
    }

}