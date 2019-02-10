package com.backend.registraion.Service;

import com.backend.registraion.Dao.UserDao;
import com.backend.registraion.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;
    private String reg ="\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])\\S{8,}\\z";

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
    public synchronized boolean addUser(User user){
        if(userDao.getUserByEmail(user.getEmail())||!user.getPassword().matches(reg))
            return false;
        else {
            userDao.addUser(user);
            return true;
        }

    }

}
