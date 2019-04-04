package com.e_learning_system.services;

import com.e_learning_system.dao.UsersOnCoursesRepository;
import com.e_learning_system.entities.UsersOnCoursesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOnCoursesService {
    private final UsersOnCoursesRepository usersOnCoursesRepository;

    @Autowired
    public UserOnCoursesService(UsersOnCoursesRepository usersOnCoursesRepository) {
        this.usersOnCoursesRepository = usersOnCoursesRepository;
    }
    public void joinToCourse(Long userId, Long courseId){
        usersOnCoursesRepository.save(new UsersOnCoursesEntity(userId,courseId));
    }
    public boolean checkUser(Long userId, Long courseId){
        return usersOnCoursesRepository.getByCourseIdAndUserId(
                userId,
                courseId).size() > 0;
    }
}
