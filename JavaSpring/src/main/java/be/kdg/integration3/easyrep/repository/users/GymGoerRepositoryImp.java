package be.kdg.integration3.easyrep.repository.users;


import be.kdg.integration3.easyrep.model.GymGoer;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GymGoerRepositoryImp implements GymGoerRepository {

    private static final Logger log = LoggerFactory.getLogger(GymGoerRepositoryImp.class);
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void save(GymGoer gymGoer) {
        log.info("Saving gymGoer: {}", gymGoer);
        em.persist(gymGoer);
    }

    @Transactional
    @Override
    public void deleteGymGoer(GymGoer gymGoer) {
        log.info("Deleting gymGoer with ID: {}", gymGoer.getUserId());

        // Ensure the gymGoer is managed
        GymGoer managedGymGoer = em.find(GymGoer.class, gymGoer.getUserId()); // Fetch the entity by ID
        if (managedGymGoer != null) {
            em.remove(managedGymGoer); // Remove the managed entity
        } else {
            throw new IllegalArgumentException("GymGoer with ID " + gymGoer.getUserId() + " not found, cannot delete.");
        }
    }


    @Override
    public GymGoer findByUserId(int userId) {
        log.info("Finding gymGoer by userId: {}", userId);
        return em.find(GymGoer.class, userId);
    }

    @Override
    public List<GymGoer> findAll() {
        log.info("Finding all gymGoers");
        String jpql = "SELECT g FROM GymGoer g";
        return em.createQuery(jpql, GymGoer.class).getResultList();
    }
}

