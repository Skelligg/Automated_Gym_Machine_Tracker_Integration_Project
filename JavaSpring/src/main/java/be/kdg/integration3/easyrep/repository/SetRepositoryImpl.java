package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SetRepositoryImpl implements SetRepository {

    private final Logger logger = LoggerFactory.getLogger(SetRepositoryImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ExerciseSet> findAllExerciseSet(){
        logger.info("Find all exercise sets");
        return  em.createQuery("select e from ExerciseSet e", ExerciseSet.class).getResultList();
    }

    @Override
    public ExerciseSet findExerciseSetById(int id) {
        logger.info("Find exercise set by id: {}", id);
        return em.find(ExerciseSet.class, id);
    }

    @Transactional
    @Override
    public ExerciseSet createExerciseSet(ExerciseSet exerciseSet) {
        logger.info("Create exercise set: {}", exerciseSet);
        em.persist(exerciseSet);
        return exerciseSet;
    }

    @Transactional
    @Override
    public void updateSet(ExerciseSet exerciseSet) {
        logger.info("Update exercise set");
        em.merge(exerciseSet);

    }

    @Transactional
    @Override
    public void delete(ExerciseSet exerciseSet) {
        logger.info("Delete exercise set: {}", exerciseSet);
        if (!em.contains(exerciseSet)) {
            System.out.println("DETACHED EXERCISE SET");
            exerciseSet = em.merge(exerciseSet);
        }
        em.remove(exerciseSet);
    }


}
