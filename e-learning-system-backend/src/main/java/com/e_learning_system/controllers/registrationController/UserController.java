package com.e_learning_system.controllers.registrationController;

import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.dto.SignUpDto;
import com.e_learning_system.entities.ConfirmationToken;
import com.e_learning_system.entities.User;
import com.e_learning_system.services.registrationService.ConfirmationTokenService;
import com.e_learning_system.services.registrationService.EmailSenderService;
import com.e_learning_system.services.UserGroupsService;
import com.e_learning_system.services.registrationService.UserService;
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
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;
    private final ModelMapperUtil modelMapperUtil;
    private final UserGroupsService userGroupsService;
    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenService confirmationTokenService, EmailSenderService emailSenderService, ModelMapperUtil modelMapperUtil, UserGroupsService userGroupsService) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSenderService = emailSenderService;
        this.modelMapperUtil = modelMapperUtil;
        this.userGroupsService = userGroupsService;
    }




    @PreAuthorize("hasAuthority('user')")
    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("user")
    public ResponseEntity<Void> addUser(@RequestBody SignUpDto signUpDto) {
        if (userService.checkUser(signUpDto.getPassword())) {
            User user = modelMapperUtil.map(signUpDto, User.class);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            boolean flag = userService.addUser(user);
            user.setReg_id(userGroupsService.getUserGroupsByGroupName(
                    signUpDto.getProfession()
                    )
                            .getId()
            );
            if (!flag)
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            else {
                ConfirmationToken confirmationToken = new ConfirmationToken(user);
                user.setReg_id(3L);
                confirmationTokenService.save(confirmationToken);
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(user.getEmail());
                mailMessage.setSubject("Complete Registration!");
                mailMessage.setText("To confirm your account, please click here : "
                        + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());

                emailSenderService.sendEmail(mailMessage);

                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
