package com.e_learning_system.controllers;

import com.e_learning_system.dto.UpdateUserDto;
import com.e_learning_system.dto.UserDto;
import com.e_learning_system.entities.User;
import com.e_learning_system.googleApi.GoogleDriveService;
import com.e_learning_system.security.service.UserPrinciple;
import com.e_learning_system.services.FileExchangeService;
import com.e_learning_system.services.UserInfoService;
import com.e_learning_system.services.registrationService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserEditController extends BaseGetController {

    private final UserInfoService userInfoService;
    private final FileExchangeService fileExchangeService;
    private final GoogleDriveService googleDriveService;
    private final UserService userService;

    @Autowired
    public UserEditController(UserInfoService userInfoService, FileExchangeService fileExchangeService, GoogleDriveService googleDriveService, UserService userService) {
        this.userInfoService = userInfoService;
        this.fileExchangeService = fileExchangeService;
        this.googleDriveService = googleDriveService;
        this.userService = userService;
    }

    @PostMapping("/img")
    public ResponseEntity<String> uploadImg(@RequestParam("file") MultipartFile file) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userService.getUserById(userPrinciple.getId());
        com.google.api.services.drive.model.File result = fileExchangeService.uploadPageImg(file);
        if (result != null) {
            if (user.getUserInfo().getAvatarUrl() != null
            ) {
                googleDriveService.deleteFile(
                        googleDriveService.getDriveService(),
                        userInfoService.getFileIdFromUrl(
                                user.getUserInfo().getAvatarUrl()
                        )
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(result.getWebContentLink());
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto userDto) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return new ResponseEntity<>(userInfoService.updateUserInfo(userPrinciple.getId(), userDto), HttpStatus.OK);
    }
}
