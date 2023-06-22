package com.project.welltrip.dao;

import com.project.welltrip.domain.Dm;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DmDao {
//    List<Dm> getDmsByUserId(String userId) throws DataAccessException;

    Dm getDm(int dmId) throws DataAccessException;

    void insertDm(Dm dm) throws DataAccessException;

    void deleteDm(Dm dm) throws DataAccessException;
}
