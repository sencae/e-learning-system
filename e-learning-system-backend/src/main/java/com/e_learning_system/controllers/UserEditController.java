package com.e_learning_system.controllers;

import com.e_learning_system.dto.UserDto;
import com.e_learning_system.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEditController extends BaseGetController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserEditController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/edit")
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto) {
        userDto.setUniversity("fff");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
