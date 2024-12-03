package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.GymStaff;
import be.kdg.integration3.easyrep.model.UserCredentials;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.GymStaff;
import be.kdg.integration3.easyrep.model.UserCredentials;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GymStaffRepositoryImp implements GymStaffRepository {
    Logger logger = LoggerFactory.getLogger(GymStaffRepositoryImp.class);

    @PersistenceContext
    EntityManager em;

//    static {
//        gymStaffList.add(new GymStaff(1,1));
//    }

    @Transactional
    @Override
    public void save(GymStaff gymStaff) {
        logger.info("Saving gym staff: {}", gymStaff);
        em.persist(gymStaff);
    }

    @Transactional
    @Override
    public void remove(GymStaff gymStaff) {
        logger.info("Removing gym staff with ID: {}", gymStaff.getUserId());

        // Ensure the gymStaff is managed
        GymStaff managedGymStaff = em.find(GymStaff.class, gymStaff.getUserId()); // Fetch the entity by ID
        if (managedGymStaff != null) {
            em.remove(managedGymStaff); // Remove the managed entity
        } else {
            throw new IllegalArgumentException("GymStaff with ID " + gymStaff.getUserId() + " not found, cannot remove.");
        }
    }


    @Override
    public GymStaff findByUserId(int userId) {
        logger.info("Finding gym staff by id: {}", userId);
        return em.find(GymStaff.class, userId);
    }

    @Override
    public List<GymStaff> findAll() {
        logger.info("Finding all gym staff");
        String jpql = "SELECT g FROM GymStaff g";
        return em.createQuery(jpql, GymStaff.class).getResultList();
    }

}

