package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Gym;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class GymRepositoryImpl implements GymRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Gym> findAllGyms() {
        logger.info("Getting all Gyms");
        return em.createQuery("select g from Gym g", Gym.class).getResultList();
    }

    @Override
    public Gym findById(int id) {
        logger.info("Getting Gym by id: {}", id);
        return em.find(Gym.class, id);
    }

    @Transactional
    @Override
    public Gym create(Gym gym) {
        logger.info("Creating gym");
        em.persist(gym);
        return gym;
    }

    @Transactional
    @Override
    public void delete(Gym gym) {
        logger.info("Deleting gym " + gym.getName());
        if (!em.contains(gym)) {
            System.out.println("DETACHED GYM");
            gym = em.merge(gym);
        }
        em.remove(gym);

    }

    @Transactional
    @Override
    public void update(Gym gym) {
        em.merge(gym);
    }

}
