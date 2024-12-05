package be.kdg.integration3.easyrep.repository.routines;


import be.kdg.integration3.easyrep.model.Routine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoutineRepository {

    private static final Logger log = LogManager.getLogger(RoutineRepository.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createRoutine(Routine routine) {
        em.persist(routine);
    }

    @Transactional
    public void removeRoutine(Routine routine) {
        Routine managedRoutine = em.find(Routine.class, routine.getRoutine_id());
        if (managedRoutine != null) {
            em.remove(managedRoutine);
        } else {
            throw new IllegalArgumentException("Routine with ID " + routine.getRoutine_id() + " not found, cannot remove.");
        }
    }


    @Transactional
    public Routine getRoutine(int id) {
        return em.find(Routine.class, id);
    }

    public List<Routine> getAllRoutines() {
        log.info("Retrieving all Routines");
        return em.createQuery("select r from Routine r", Routine.class).getResultList();
    }


    @Transactional
    public void updateRoutineExercises(Routine routine) {
        Routine routineTemp = em.find(Routine.class, routine.getRoutine_id());
        if (routineTemp != null) {
            routineTemp.setExercises(routine.getExercises());
            log.info("Updated routine with ID: {}", routine.getRoutine_id());
        } else {
            log.warn("Routine with ID {} not found. Update failed.", routine.getRoutine_id());
            throw new IllegalArgumentException("Routine with ID " + routine.getRoutine_id() + " not found");
        }
    }


    public Routine getRoutineByName(String routineName) {
        String jpql = "SELECT r FROM Routine r WHERE LOWER(r.name) LIKE LOWER(:routineName)";
        return em.createQuery(jpql, Routine.class)
                .setParameter("routineName", "%" + routineName + "%") // Partial match with wildcards
                .getResultStream()
                .findFirst()
                .orElse(null); // Return null if no match is found
    }

}
