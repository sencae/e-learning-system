package com.e_learning_system.createCourse.controller;

import com.e_learning_system.createCourse.service.CoursesService;
import com.e_learning_system.entities.Courses;
import com.e_learning_system.googleApi.GoogleDriveService;
import com.e_learning_system.security.service.UserPrinciple;
import com.e_learning_system.dto.CoursesDto;
import com.e_learning_system.dto.ModelMapperUtil;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("courses")
public class CoursesController {
    private final CoursesService coursesService;
    private final GoogleDriveService googleDriveService;
    @Autowired
    public CoursesController(CoursesService coursesService, GoogleDriveService googleDriveService) {
        this.coursesService = coursesService;
        this.googleDriveService = googleDriveService;
    }


    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("create")
    public ResponseEntity<Void> createCourse(@RequestBody Courses course) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        course.setProfessorId(userPrinciple.getId());
        coursesService.createCourse(course);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("test")
    public void test(){
//        File file =new File("C:\\Users\\Admin\\Desktop\\0ZXvMlCy-LY.jpg");
//        com.google.api.services.drive.model.File file2 = googleDriveService.uploadFile(file.getName(),
//                file.getAbsolutePath(),
//                "image/jpg");
//
//        try{
//            System.err.println(file2.toPrettyString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    @GetMapping("get")
    public void get(){
        googleDriveService.getFilesList();
    }
    @GetMapping("delete")
    public void delete(){
        googleDriveService.deleteFile(googleDriveService.getDriveService(),"17DRdXeRJkySX7lPDkIvlLQq_SIbtgNFu");
        googleDriveService.deleteFile(googleDriveService.getDriveService(),"1s6474nLdEW_WRhRcckG66Aj9_V2-NVn4");
    }
    @GetMapping("download")
    public void down(){
        try {
            googleDriveService.downloadFile("1rl_G74flZESBrh8-jKCemT36vxsfs1Dp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
