package com.e_learning_system.controllers;

import com.e_learning_system.googleApi.GoogleDriveService;
import com.e_learning_system.security.service.UserPrinciple;
import com.e_learning_system.services.CoursesService;
import com.e_learning_system.services.FileExchangeService;
import com.e_learning_system.services.UserInfoService;
import com.e_learning_system.services.registrationService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FileExchangeController extends BaseGetController {
    private final GoogleDriveService googleDriveService;
    private final UserInfoService userInfoService;
    private final CoursesService coursesService;
    private final UserService userService;
    private final FileExchangeService fileExchangeService;

    @Autowired
    public FileExchangeController(GoogleDriveService googleDriveService,
                                  UserInfoService userInfoService,
                                  CoursesService coursesService,
                                  UserService userService, FileExchangeService fileExchangeService) {
        this.googleDriveService = googleDriveService;
        this.userInfoService = userInfoService;
        this.coursesService = coursesService;
        this.userService = userService;
        this.fileExchangeService = fileExchangeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> UploadPageImg(@RequestParam("file") MultipartFile file) {
        String message = "";
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        com.google.api.services.drive.model.File result = fileExchangeService.uploadPageImg(file);
        if (result != null) {
            googleDriveService.deleteFile(
                    googleDriveService.getDriveService(),
                    userInfoService.getFileIdFromUrl(
                            userService.getUserById(
                                    userPrinciple.getId()
                            ).getUserInfo().getAvatarUrl()
                    )
            );
            if (userInfoService.setPageImgUrl(result.getWebContentLink(), userPrinciple.getId()))
                message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }


    @GetMapping("/getallfiles")
    public ResponseEntity<List<String>> getListFiles(Model model) {
        List<String> files = new ArrayList<String>();
        List<com.google.api.services.drive.model.File> Gfiles = googleDriveService.getFilesList();
        for (com.google.api.services.drive.model.File file : Gfiles) {
            files.add(file.getName());
        }
        return ResponseEntity.ok().body(files);
    }

    @PostMapping("/delete/profimg")
    public ResponseEntity<String> deleteProfileImage(@RequestBody String url) {
        if (
                googleDriveService.deleteFile(googleDriveService.getDriveService(), userInfoService.getFileIdFromUrl(url)) &&
                        userInfoService.deletePageImgUrl(url))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/delete/courseres")
    public ResponseEntity<String> deleteCourseResource(@RequestBody String url) {

        if (
                googleDriveService.deleteFile(googleDriveService.getDriveService(), userInfoService.getFileIdFromUrl(url)) &&
                        coursesService.deleteResource(url))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/uploadres")
    public ResponseEntity<Void> uploadResource(@RequestParam("files") MultipartFile[] files,@RequestParam("topic")Long id){
        Integer numberOfFiles = fileExchangeService.uploadResources(files,id);
        if(numberOfFiles==files.length) {
            return new ResponseEntity<>( HttpStatus.OK);
        }
        else {

            return new  ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

