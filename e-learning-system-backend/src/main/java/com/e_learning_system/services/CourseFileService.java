package com.e_learning_system.services;

import com.e_learning_system.dao.CourseFileRepository;
import com.e_learning_system.entities.CourseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseFileService {
    private final CourseFileRepository courseFileRepository;

    @Autowired
    public CourseFileService(CourseFileRepository courseFileRepository) {
        this.courseFileRepository = courseFileRepository;
    }

    public CourseFile save(CourseFile courseFile){
        return courseFileRepository.save(courseFile);
    }
    public CourseFile getByUrl(String url){
        return courseFileRepository.getByUrl(url);
    }
    @Transactional
    public void deleteResource(CourseFile courseFile) {
        this.courseFileRepository.delete(courseFile);
    }
}
