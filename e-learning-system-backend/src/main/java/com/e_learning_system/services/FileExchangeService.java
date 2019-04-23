package com.e_learning_system.services;

import com.e_learning_system.dao.DetachObject;
import com.e_learning_system.entities.CourseResources;
import com.e_learning_system.googleApi.GoogleDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

@Service
public class FileExchangeService {
    private final GoogleDriveService googleDriveService;
    private final ResourcesService resourcesService;

    private final DetachObject detachObject;
    @Autowired
    public FileExchangeService(GoogleDriveService googleDriveService, ResourcesService resourcesService, DetachObject detachObject) {
        this.googleDriveService = googleDriveService;
        this.resourcesService = resourcesService;
        this.detachObject = detachObject;
    }

    public com.google.api.services.drive.model.File uploadPageImg(MultipartFile file) {
        File fileUpload = googleDriveService.multipartToFile(file);
        if (file != null && Objects.requireNonNull(file.getContentType()).equals(MimeTypeUtils.IMAGE_JPEG_VALUE))
            return googleDriveService.uploadFile(
                    file.getOriginalFilename(),
                    fileUpload, file.getContentType());
        else
            return null;
    }

    public Integer uploadResources(MultipartFile[] files, Long topicId) {
        Integer fileUploadCounter = 0;
        CourseResources courseResources = new CourseResources();
        for (MultipartFile file : files) {
            detachObject.DetachCourseResources(courseResources);
            courseResources.setId(null);
            File fileUpload = googleDriveService.multipartToFile(file);
            com.google.api.services.drive.model.File res = googleDriveService.uploadFile(
                    file.getOriginalFilename(),
                    fileUpload, file.getContentType());
            courseResources.setTitle(file.getOriginalFilename());
            courseResources.setUrl(res.getWebContentLink());
            courseResources.setTopicId(topicId);
            courseResources = resourcesService.saveResource(courseResources);
            fileUploadCounter++;
            if(!fileUpload.delete()) {
                break;
            }
        }
        return fileUploadCounter;
    }
}

