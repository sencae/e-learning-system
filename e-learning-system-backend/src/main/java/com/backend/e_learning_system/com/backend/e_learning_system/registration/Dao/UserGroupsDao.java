package com.backend.e_learning_system.com.backend.e_learning_system.registration.Dao;

import com.backend.e_learning_system.com.backend.e_learning_system.registration.Entity.UserGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserGroupsDao {
    @Autowired
    private EntityManager entityManager;

    public UserGroups getUserGroupsById(long id) {
        String sql = "from UserGroups as ug where ug.id = :id";
        return (UserGroups) entityManager.createQuery(sql).setParameter("id", id).getSingleResult();
    }

}
