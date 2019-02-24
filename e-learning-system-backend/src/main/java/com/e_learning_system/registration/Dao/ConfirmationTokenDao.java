package com.e_learning_system.registration.Dao;

import com.e_learning_system.registration.Entity.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ConfirmationTokenDao {
    private final EntityManager entityManager;

    @Autowired
    public ConfirmationTokenDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(ConfirmationToken confirmationToken) {
        entityManager.persist(confirmationToken);
    }

    public ConfirmationToken findByConfirmationToken(String confirmationToken) {
        try {
            return (ConfirmationToken) entityManager.createQuery(
                    "from ConfirmationToken as ct where ct.confirmation_Token=:conft")
                    .setParameter("conft", confirmationToken).getSingleResult();
        } catch (Exception ex) {
            return null;
        }

    }

    public void deleteConfirmationToken(ConfirmationToken confirmationToken) {
        entityManager.remove(confirmationToken);
    }

}