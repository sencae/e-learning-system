package com.e_learning_system.controllers;

import com.e_learning_system.entities.UsersOnCoursesEntity;
import com.e_learning_system.services.CoursesService;
import com.e_learning_system.entities.Courses;
import com.e_learning_system.googleApi.GoogleDriveService;
import com.e_learning_system.security.service.UserPrinciple;
import com.e_learning_system.services.UserOnCoursesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("courses")
public class CoursesController {
    private static final Logger logger = LoggerFactory.getLogger(CoursesController.class);
    private final CoursesService coursesService;
    private final GoogleDriveService googleDriveService;
    private final UserOnCoursesService userOnCoursesService;
    @Autowired
    public CoursesController(CoursesService coursesService, GoogleDriveService googleDriveService, UserOnCoursesService userOnCoursesService) {
        this.coursesService = coursesService;
        this.googleDriveService = googleDriveService;
        this.userOnCoursesService = userOnCoursesService;
    }


    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("create")
    public ResponseEntity<Long> createCourse(@RequestBody Courses course) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        course.setProfessorId(userPrinciple.getId());
        coursesService.createCourse(course);

        return new ResponseEntity<>(course.getId(),HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('student')")
    @PostMapping("join")
    public ResponseEntity<Void> joinToCourses(@RequestBody Long courseId){
        if(coursesService.getCourseById(courseId).getStartDate().getTime()>System.currentTimeMillis())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            userOnCoursesService.joinToCourse(userPrinciple.getId(),courseId);        }
        catch (Exception ex){
            logger.error("Error. Message - {}",ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
