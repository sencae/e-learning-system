package com.e_learning_system.services;

import com.e_learning_system.dao.DetachObject;
import com.e_learning_system.entities.CourseResources;
import com.e_learning_system.entities.ResourcesOfCourseEntity;
import com.e_learning_system.googleApi.GoogleDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileExchangeService {
    private final GoogleDriveService googleDriveService;
    private final ResourcesService resourcesService;
    private final ResourcesOfCourseService resourcesOfCourseService;

    private final DetachObject detachObject;
    @Autowired
    public FileExchangeService(GoogleDriveService googleDriveService, ResourcesService resourcesService, ResourcesOfCourseService resourcesOfCourseService, DetachObject detachObject) {
        this.googleDriveService = googleDriveService;
        this.resourcesService = resourcesService;
        this.resourcesOfCourseService = resourcesOfCourseService;
        this.detachObject = detachObject;
    }

    public com.google.api.services.drive.model.File uploadPageImg(MultipartFile file) {
        File fileUpload = googleDriveService.multipartToFile(file);
        return googleDriveService.uploadFile(
                file.getOriginalFilename(),
                fileUpload, file.getContentType());
    }

    public Integer uploadResources(MultipartFile[] files, Long courseId) {
        Integer fileUploadCounter = 0;
        CourseResources courseResources = new CourseResources();
        ResourcesOfCourseEntity resourcesOfCourseEntity = new ResourcesOfCourseEntity();
        for (MultipartFile file : files) {
            detachObject.DetachCourseResources(courseResources);
            detachObject.DetachResourcesOfCourse(resourcesOfCourseEntity);
            courseResources.setId(null);
            resourcesOfCourseEntity.setId(null);
            File fileUpload = googleDriveService.multipartToFile(file);
            com.google.api.services.drive.model.File res = googleDriveService.uploadFile(
                    file.getOriginalFilename(),
                    fileUpload, file.getContentType());
            courseResources.setTitle(file.getOriginalFilename());
            courseResources.setUrl(res.getWebContentLink());
            courseResources = resourcesService.saveResource(courseResources);
            resourcesOfCourseEntity.setCourseId(courseId);
            resourcesOfCourseEntity.setResourceId(courseResources.getId());

            resourcesOfCourseService.save(resourcesOfCourseEntity);
            fileUploadCounter++;
            if(!fileUpload.delete()) {
                break;
            }
        }
        return fileUploadCounter;
    }
}

