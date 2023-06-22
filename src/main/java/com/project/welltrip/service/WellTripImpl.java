package com.project.welltrip.service;

import com.project.welltrip.dao.DmDao;
import com.project.welltrip.dao.UserDao;
import com.project.welltrip.domain.Dm;
import com.project.welltrip.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WellTripImpl implements WellTripFacade{

    @Autowired
    @Qualifier("jpaUserDao")
    private UserDao userDao;

    @Autowired
    @Qualifier("jpaDmDao")
    private DmDao dmDao;

    @Override
    public User getUser(String email) {
        return null;
    }

    @Override
    public User getUser(String email, String password) {
        return userDao.getUser(email, password);
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public List<String> getUserIdList() {
        return null;
    }


    @Override
    public void insertDm(Dm dm){
        dmDao.insertDm(dm);
    }

}
