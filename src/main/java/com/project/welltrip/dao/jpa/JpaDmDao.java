package com.project.welltrip.dao.jpa;

import com.project.welltrip.dao.DmDao;
import com.project.welltrip.dao.SequenceDao;
import com.project.welltrip.domain.Dm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaDmDao implements DmDao {

    @PersistenceContext
    private EntityManager em;

//    @Autowired
//    private SequenceDao sequenceDao;

//    @Override
//    public List<Dm> getDmsByUserId(String userId) throws DataAccessException {
//        TypedQuery<Dm> query = em.createQuery("select dm from Dm dm " + "where dm.userId=?1", Dm.class);
//        query.setParameter(1, userId);
//        return query.getResultList();
//    }

    @Override
    public Dm getDm(int dmId) throws DataAccessException {
        return em.find(Dm.class, dmId);
    }

    @Override
    public void insertDm(Dm dm) throws DataAccessException {
//        Long newDmId = (long) sequenceDao.getNextId("ordernum");
//        dm.setDmId(newDmId);
//        for (int i = 0; i < dm.getLineItems().size(); i++) {
//            LineItem lineItem = (LineItem) order.getLineItems().get(i);
//            lineItem.setOrderId(newOrderId);
//        }
        em.persist(dm);
    }

    @Override
    public void deleteDm(Dm dm) throws DataAccessException {

    }
}
