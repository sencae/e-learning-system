package com.e_learning_system.services;

import com.e_learning_system.dao.CoursesRep;
import com.e_learning_system.dao.UsersOnCoursesRepository;
import com.e_learning_system.entities.Courses;
import com.e_learning_system.entities.User;
import com.e_learning_system.entities.UsersOnCoursesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserOnCoursesService {
    private final UsersOnCoursesRepository usersOnCoursesRepository;
    private final CoursesRep coursesRep;

    @Autowired
    public UserOnCoursesService(UsersOnCoursesRepository usersOnCoursesRepository, CoursesRep coursesRep) {
        this.usersOnCoursesRepository = usersOnCoursesRepository;
        this.coursesRep = coursesRep;
    }

    public void joinToCourse(Long userId, Long courseId) {
        usersOnCoursesRepository.save(new UsersOnCoursesEntity(userId, courseId));
    }

    public boolean checkUser(Long userId, Long courseId) {
        return usersOnCoursesRepository.getByCourseIdAndUserId(
                courseId,
                userId).size() > 0;
    }

    public Set<User> getUsersByCourseId(Long id, Long userId) {
        Courses courses = coursesRep.getById(id);
        if (courses.getProfessorId().equals(userId))
            return courses.getUsersOnCourse();
        else
            return null;
    }
    public UsersOnCoursesEntity getUserOnCourseByUserIdAndCourseId(Long userId,Long courseId){
        return usersOnCoursesRepository.getByCourseIdAndUserId(courseId,userId).get(0);
    }
    public void save(UsersOnCoursesEntity usersOnCoursesEntity){
        usersOnCoursesRepository.save(usersOnCoursesEntity);
    }
}
