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

    //@Valid 해줘야 돌아감...ㅜㅜ
    @Valid
    private User user;

    private boolean newUser;

    @NotEmpty
    private String confirmPassword;

    public UserDto() {
        this.user = new User();
        this.newUser = true;
    }

    public UserDto(User user) {
        this.user = user;
        this.newUser = false;
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

    public boolean isSamePassword() {
        return user.getPassword().equals(confirmPassword);
    }

}
