package com.cts.fsd.userTask.dao;


import com.cts.fsd.userTask.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class UserMgrDao {

    @PersistenceContext
    private EntityManager em;
    @Transactional
    public User addUser(User user){

        User userUpdated=em.merge(user);
        return userUpdated;

    }

    @Transactional
    public void updateUser(User user) {

        em.merge(user);
    }

    @Transactional
    public void deleteUser(Integer userId){
        User user=viewUser(userId);
        em.remove(user);
    }

    @Transactional
    public List<User> listUser(){
        String queryString= "from User a";

        Query query= em.createQuery(queryString);
        List<User> queryResult=query.getResultList();

       return queryResult;

    }


    @Transactional
    public User viewUser(Integer userId) {
        List<User> queryResult=null;
        Query query= em.createQuery("from User where id = :userId");
        query.setParameter("userId",userId);
        queryResult =query.getResultList();
        if (queryResult.size() >0){
            return queryResult.get(0);
        }
        return null;
    }
}

