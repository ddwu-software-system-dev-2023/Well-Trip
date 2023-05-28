package com.project.welltrip.service;

import com.project.welltrip.domain.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WellTripImpl implements WellTripFacade{

    @Override
    public User getUser(String userId) {
        return null;
    }

    @Override
    public User getUser(String userId, String password) {
        return null;
    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public List<String> getUserIdList() {
        return null;
    }
}
