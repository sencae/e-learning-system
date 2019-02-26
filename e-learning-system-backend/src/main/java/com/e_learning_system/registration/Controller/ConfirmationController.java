package com.e_learning_system.registration.Controller;


import com.e_learning_system.dao.ConfirmationTokenDao;
import com.e_learning_system.dao.UserGroupsDao;
import com.e_learning_system.entities.ConfirmationToken;
import com.e_learning_system.entities.User;
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

    private final ConfirmationTokenDao confirmationTokenDao;
    private final UserService userService;
    private final UserGroupsDao userGroupsDao;

    @Autowired
    public ConfirmationController(ConfirmationTokenDao confirmationTokenDao, UserService userService,
                                  UserGroupsDao userGroupsDao) {
        this.confirmationTokenDao = confirmationTokenDao;
        this.userService = userService;
        this.userGroupsDao = userGroupsDao;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenDao.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userService.getUserByEmailAuth(token.getUser().getEmail());
            user.setUserGroupsByRegId(userGroupsDao.getUserGroupsById(2));
            userService.updateUser(user);
            confirmationTokenDao.deleteConfirmationToken(token);
            return new ResponseEntity<>("well done", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("oh no", HttpStatus.CONFLICT);
        }
    }

}
