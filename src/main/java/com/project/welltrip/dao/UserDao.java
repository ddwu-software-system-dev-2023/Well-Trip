package com.project.welltrip.dao;

import com.project.welltrip.domain.User;
import org.springframework.dao.DataAccessException;

import javax.validation.Valid;
import java.util.List;

public interface UserDao {

    User getUser(String email) throws DataAccessException;

    User getUser(String email, String password) throws DataAccessException;

    void insertUser(User user) throws DataAccessException;

    void updateUser(User user) throws DataAccessException;

    List<String> getUserIdList() throws DataAccessException;
}
