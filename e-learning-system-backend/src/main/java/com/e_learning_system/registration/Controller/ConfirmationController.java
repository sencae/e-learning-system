package com.e_learning_system.registration.Controller;


import com.e_learning_system.entities.ConfirmationToken;
import com.e_learning_system.entities.User;
import com.e_learning_system.registration.Service.ConfirmationTokenService;
import com.e_learning_system.registration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenService.getByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userService.getUserByEmailAuth(token.getUser().getEmail());
            user.setReg_id( Long.valueOf(token.getConfirmationToken().substring(token.getConfirmationToken().length()-1)));
            userService.updateUser(user);
            confirmationTokenService.delete(token);
            return new ResponseEntity<>("well done", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("oh no", HttpStatus.CONFLICT);
        }
    }

}
