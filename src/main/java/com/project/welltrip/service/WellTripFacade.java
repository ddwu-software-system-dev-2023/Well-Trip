package com.project.welltrip.service;

import com.project.welltrip.domain.User;

import java.util.List;

public interface WellTripFacade {

    User getUser(String userId);

    User getUser(String userId, String password);

    void insertUser(User user);

    void updateUser(User user);

    List<String> getUserIdList();


}