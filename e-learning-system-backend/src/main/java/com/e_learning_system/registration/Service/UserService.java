package com.e_learning_system.registration.Service;

import com.e_learning_system.dao.UserRepository;
import com.e_learning_system.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private String reg = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])\\S{8,}\\z";
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User getUserByEmailAuth(String email) {
        return userRepository.getUserByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean checkUser(String password) {
        return password.matches(reg);
    }

    public boolean addUser(User user) {
        if (userRepository.getUserByEmaila(user.getEmail()))
            return false;
        else {
            user.setReg_id(3L);
            userRepository.save(user);
            return true;
        }

    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

}
