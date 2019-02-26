package com.e_learning_system.registration.Controller;

import com.e_learning_system.dao.ConfirmationTokenDao;
import com.e_learning_system.entities.ConfirmationToken;
import com.e_learning_system.entities.User;
import com.e_learning_system.registration.Service.EmailSenderService;
import com.e_learning_system.registration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("registration")
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenDao confirmationTokenDao;
    private final EmailSenderService emailSenderService;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenDao confirmationTokenDao, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenDao = confirmationTokenDao;
        this.emailSenderService = emailSenderService;
    }


    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('user')")
    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("user")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        boolean flag = userService.addUser(user);
        if (!flag)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenDao.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


}
