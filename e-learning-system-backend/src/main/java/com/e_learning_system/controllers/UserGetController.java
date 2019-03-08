package com.e_learning_system.controllers;

import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.dto.UserDto;
import com.e_learning_system.entities.User;
import com.e_learning_system.registration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserGetController extends BaseGetController {

    private final UserService userService;
    private final ModelMapperUtil modelMapperUtil;

    @Autowired
    public UserGetController(UserService userService, ModelMapperUtil modelMapperUtil) {
        this.userService = userService;
        this.modelMapperUtil = modelMapperUtil;
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        UserDto userDto = modelMapperUtil.map(user, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
