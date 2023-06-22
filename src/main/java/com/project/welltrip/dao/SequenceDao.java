package com.project.welltrip.dao;

import org.springframework.dao.DataAccessException;

public interface SequenceDao {
    public int getNextId(String name) throws DataAccessException;
}
