package com.e_learning_system.controllers;

import com.e_learning_system.dto.CourseResourcesDto;
import com.e_learning_system.entities.*;
import com.e_learning_system.googleApi.GoogleDriveService;
import com.e_learning_system.security.service.UserPrinciple;
import com.e_learning_system.services.*;
import com.e_learning_system.services.registrationService.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ResourcesService resourcesService;
    private final TopicsService topicsService;
    private final UtilService utilService;

    private final ObjectMapper objectMapper;
    private final CourseFileService courseFileService;

    @Autowired
    public FileExchangeController(GoogleDriveService googleDriveService,
                                  UserInfoService userInfoService,
                                  CoursesService coursesService,
                                  UserService userService, FileExchangeService fileExchangeService, ResourcesService resourcesService, TopicsService topicsService, UtilService utilService, ObjectMapper objectMapper, CourseFileService courseFileService) {
        this.googleDriveService = googleDriveService;
        this.userInfoService = userInfoService;
        this.coursesService = coursesService;
        this.userService = userService;
        this.fileExchangeService = fileExchangeService;
        this.resourcesService = resourcesService;
        this.topicsService = topicsService;
        this.utilService = utilService;
        this.objectMapper = objectMapper;
        this.courseFileService = courseFileService;
    }


    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/uploadCourseImg")
    public ResponseEntity<String> uploadCourseImg(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        String message;
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Courses course = coursesService.getCourseById(id);
        if (course.getProfessorId().equals(userPrinciple.getId())) {
            com.google.api.services.drive.model.File result = fileExchangeService.uploadPageImg(file);
            if (result != null) {
                if (course.getUrl() != null)
                    googleDriveService.deleteFile(
                            googleDriveService.getDriveService(),
                            utilService.getFileIdFromUrl(
                                    course.getUrl()
                            )
                    );
                course.setUrl(result.getWebContentLink());
                coursesService.saveCourse(course);
                return new ResponseEntity<>(result.getWebContentLink(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            message = "Restricted";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
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
    public ResponseEntity<Void> deleteProfileImage(@RequestBody String url) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userService.getUserById(userPrinciple.getId());
        if (
                user.getUserInfo().getAvatarUrl().equals(url) &&
                        googleDriveService.deleteFile(googleDriveService.getDriveService(),
                                utilService.getFileIdFromUrl(user.getUserInfo().getAvatarUrl())
                        )
        ) {
            user.getUserInfo().setAvatarUrl(null);
            userService.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/delete/courseimg")
    public ResponseEntity<Void> deleteCourseImage(@RequestBody String url) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Courses course = coursesService.getCourseByUrl(url);
        if (
                course.getProfessorId().equals(userPrinciple.getId()) &&
                        googleDriveService.deleteFile(googleDriveService.getDriveService(),
                                utilService.getFileIdFromUrl(course.getUrl())
                        )
        ) {
            course.setUrl(null);
            coursesService.saveCourse(course);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/delete/courseres")
    public ResponseEntity<String> deleteCourseResource(@RequestBody String url) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        CourseResources courseResource = resourcesService.getResourceByUrl(url);
        if (utilService.getFileIdFromUrl(url) == null) {
            coursesService.deleteResource(url);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            if (courseResource.getTopic().getCourse().getProfessorId().equals(userPrinciple.getId()) &&
                    googleDriveService.deleteFile(googleDriveService.getDriveService(), utilService.getFileIdFromUrl(url)) &&
                    coursesService.deleteResource(url))
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/delete/TaskFile")
    public ResponseEntity<String> deleteTaskFile(@RequestBody String url) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        CourseFile courseFile = courseFileService.getByUrl(url);
        if (googleDriveService.deleteFile(googleDriveService.getDriveService(), utilService.getFileIdFromUrl(url))) {
            courseFileService.deleteResource(courseFile);
            return new ResponseEntity<>(HttpStatus.OK);
        }
            else
                return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/uploadres")
    public ResponseEntity<Void> uploadResource(@RequestParam("files") MultipartFile[] files, @RequestParam("topic") Long id) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        TopicsEntity topic = topicsService.getById(id);
        if (topic.getCourse().getProfessorId().equals(userPrinciple.getId())) {
            Integer numberOfFiles = fileExchangeService.uploadResources(files, id);
            if (numberOfFiles == files.length) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {

                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/uploadreslink")
    public ResponseEntity<Void> uploadLinks(@RequestBody CourseResourcesDto courseResourcesDto) {
        TopicsEntity topic = topicsService.getById(courseResourcesDto.getTopicId());
        topic.getCourseResources().addAll(courseResourcesDto.getLinks());
        topicsService.save(topic);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('student')")
    @PostMapping("/uploadFinalTask")
    public ResponseEntity<String> uploadFinalTask(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Courses course = coursesService.getCourseById(id);
        com.google.api.services.drive.model.File result = fileExchangeService.uploadAnswers(file);
        if (result != null) {
            course.getUsersOnCourse().forEach(user -> {
                if (user.getId() == userPrinciple.getId()) {
                    user.getUsersOnCoursesEntities().forEach(Ucourse -> {
                        if (Ucourse.getCourseId().equals(course.getId()))
                            Ucourse.setCheckUrl(result.getWebContentLink());
                    });
                }
            });
            coursesService.saveCourse(course);
            return new ResponseEntity<>("ok",HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('professor')")
    @PostMapping("/uploadTaskFile")
    public ResponseEntity<String> uploadTaskFile(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        com.google.api.services.drive.model.File result = fileExchangeService.uploadAnswers(file);
        Courses course = coursesService.getCourseById(id);
        if (course.getProfessorId().equals(userPrinciple.getId())) {
            if (result != null) {
                CourseFile courseFile = new CourseFile();
                courseFile.setId(id);
                courseFile.setUrl(result.getWebContentLink());
                courseFileService.save(courseFile);
                return new ResponseEntity<>("ok", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}

