package com.project.welltrip.service;

import com.project.welltrip.domain.Dm;
import com.project.welltrip.domain.User;

import java.util.List;

public interface WellTripFacade {

    User getUser(String email);

    User getUser(String email, String password);

    void insertUser(User user);

    void updateUser(User user);

    List<String> getUserIdList();

    void insertDm(Dm dm);


}