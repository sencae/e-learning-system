package com.e_learning_system.dao;

import com.e_learning_system.entities.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CoursesDao {
    private final EntityManager entityManager;

    @Autowired
    public CoursesDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Courses> getAll() {
        String hql = " from Courses as c ORDER BY c.id";
        return (List<Courses>) entityManager.createQuery(hql).getResultList();
    }

    public Courses getById(Long id) {
        return entityManager.find(Courses.class, id);
    }

    public void add(Courses courses) {
        entityManager.persist(courses);
    }

    public void update(Courses courses) {
        entityManager.merge(courses);
    }
}
