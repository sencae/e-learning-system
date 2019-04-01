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
    public void joinToCourse(UsersOnCoursesEntity usersOnCoursesEntity){
        usersOnCoursesRepository.save(usersOnCoursesEntity);
    }
    public boolean checkUser(UsersOnCoursesEntity usersOnCoursesEntity){
        return usersOnCoursesRepository.getByCourseIdAndUserId(
                usersOnCoursesEntity.getCourseId(),
                usersOnCoursesEntity.getUserId()).size() > 0;
    }
}
