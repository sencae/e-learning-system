package com.e_learning_system.dao;

import com.e_learning_system.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao {
    private final EntityManager entityManager;
    private final UserGroupsDao userGroupsDao;

    @Autowired
    public UserDao(EntityManager entityManager, UserGroupsDao userGroupsDao) {
        this.entityManager = entityManager;
        this.userGroupsDao = userGroupsDao;
    }

    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public boolean getUserByEmail(String email) {
        return entityManager.createQuery("from User as usr where usr.email=:eml")
                .setParameter("eml", email).getResultList()
                .size() > 0;
    }

    public User getUserByEmailAuth(String email) {
        return (User) entityManager.createQuery("from User as usr where usr.email=:eml")
                .setParameter("eml", email).getSingleResult();
    }

    public List<User> getAllUsers() {
        String sql = " from User as usr ORDER BY usr.id";
        return (List<User>) entityManager.createQuery(sql).getResultList();
    }

    public void addUser(User user) {
        user.setUserGroupsByRegId(userGroupsDao.getUserGroupsById(3));
        entityManager.persist(user);
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }

}
