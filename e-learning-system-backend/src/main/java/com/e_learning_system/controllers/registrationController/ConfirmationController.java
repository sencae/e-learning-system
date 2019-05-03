package com.e_learning_system.controllers.registrationController;


import com.e_learning_system.entities.ConfirmationToken;
import com.e_learning_system.entities.User;
import com.e_learning_system.services.registrationService.ConfirmationTokenService;
import com.e_learning_system.services.registrationService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfirmationController {

    private final ConfirmationTokenService confirmationTokenService;
    private final UserService userService;

    @Autowired
    public ConfirmationController(ConfirmationTokenService confirmationTokenService, UserService userService) {
        this.confirmationTokenService = confirmationTokenService;
        this.userService = userService;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/confirm-account")
    public ResponseEntity<Void> confirmUserAccount(@RequestBody String confirmationToken) {
        ConfirmationToken token = confirmationTokenService.getByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userService.getUserById(token.getUser_id());
            user.setReg_id(Long.valueOf(token.getConfirmationToken().substring(token.getConfirmationToken().length() - 1)));
            userService.updateUser(user);
            confirmationTokenService.delete(token);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
