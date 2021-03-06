package com.e_learning_system.services;

import com.e_learning_system.dao.UserGroupsRepository;
import com.e_learning_system.entities.UserGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupsService {
    private final UserGroupsRepository userGroupsRepository;

    @Autowired
    public UserGroupsService(UserGroupsRepository userGroupsRepository) {
        this.userGroupsRepository = userGroupsRepository;
    }

    public UserGroups getUserGroupsById(long id) {
        return userGroupsRepository.getById(id);
    }
    public UserGroups getUserGroupsByGroupName(String name){
        return userGroupsRepository.getUserGroupsByGroupName(name);
    }
    public List<UserGroups> getAllCourses() {
        return userGroupsRepository.findAll();
    }
}
