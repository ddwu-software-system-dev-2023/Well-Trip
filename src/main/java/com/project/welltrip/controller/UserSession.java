package com.project.welltrip.controller;

import com.project.welltrip.domain.Dm;
import com.project.welltrip.domain.User;
import org.springframework.beans.support.PagedListHolder;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserSession implements Serializable {

    private User user;

    public UserSession(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


}