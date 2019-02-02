package com.backend.registraion.Service;

import com.backend.registraion.Dao.UserDao;
import com.backend.registraion.Dao.UserGroupsDao;
import com.backend.registraion.Entity.User;
import com.backend.registraion.Entity.UserGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
    public synchronized boolean addUser(User user){
        if(userDao.getUserByEmail(user.getEmail())||user.getPassword().length()<6)
            return false;
        else {
            userDao.addUser(user);
            return true;
        }

    }

}
