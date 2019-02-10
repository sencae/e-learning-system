package com.backend.e_learning_system.com.backend.e_learning_system.registration.Dao;

import com.backend.e_learning_system.com.backend.e_learning_system.registration.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserGroupsDao userGroupsDao;

    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public boolean getUserByEmail(String email) {
        return entityManager.createQuery("from User as usr where usr.email=:eml")
                .setParameter("eml", email).getResultList()
                .size() > 0;
    }

    public List<User> getAllUsers() {
        String sql = " from User as usr ORDER BY usr.id";
        return (List<User>) entityManager.createQuery(sql).getResultList();
    }

    public void addUser(User user) {
        user.setUserGroupsByRegId(userGroupsDao.getUserGroupsById(3));
        entityManager.persist(user);
    }

}
