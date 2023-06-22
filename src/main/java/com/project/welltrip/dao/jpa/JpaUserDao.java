package com.project.welltrip.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.project.welltrip.dao.UserDao;
import com.project.welltrip.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


@Repository
public class JpaUserDao implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public User getUser(String email) throws DataAccessException {
        return em.find(User.class, email);
    }

    public User getUser(String email, String password)
            throws DataAccessException {
        TypedQuery<User> query = em.createQuery(
                "select a from User a "
                        + "where a.email=:id and a.password=:pw",
                User.class);
        query.setParameter("id", email);
        query.setParameter("pw", password);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch(NoResultException ex) {
            return null;
        }
        return user;
    }

    public void insertUser(User user) throws DataAccessException {
        em.persist(user);
    }

    public void updateUser(User user) throws DataAccessException {
        em.merge(user);
    }

    public List<String> getUserIdList() throws DataAccessException {
        TypedQuery<String> query = em.createQuery(
                "select a.email from User a", String.class);
        return query.getResultList();
    }



    public void remove(User user) {
        em.remove(user);
    }

}