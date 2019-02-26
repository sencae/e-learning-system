package com.e_learning_system.registration.Service;

import com.e_learning_system.dao.UserDao;
import com.e_learning_system.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;
    private String reg = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])\\S{8,}\\z";

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public User getUserByEmailAuth(String email) {
        return userDao.getUserByEmailAuth(email);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public synchronized boolean addUser(User user) {
        if (userDao.getUserByEmail(user.getEmail()) || !user.getPassword().matches(reg))
            return false;
        else {
            userDao.addUser(user);
            return true;
        }

    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

}
