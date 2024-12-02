package be.kdg.integration3.easyrep.repository.routines;

import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ExerciseRepository {

    private static final Logger log = LoggerFactory.getLogger(ExerciseRepository.class);
    private static List<Exercise> exercises = new ArrayList<Exercise>();

    @PersistenceContext
    private EntityManager em;

    public ExerciseRepository() {
        exercises.add(new Exercise("row machine"));
        exercises.add(new Exercise("bench press"));
        exercises.add(new Exercise("squat machine"));
        exercises.add(new Exercise("unilateral jerk"));
    }

    @Transactional
    public void createExercise(Exercise exercise){
        em.persist(exercise);
    }

    @Transactional
    public void removeExercise(Exercise exercise) {
        Exercise managedExercise = em.find(Exercise.class, exercise.getId()); // Ensure it's managed
        if (managedExercise != null) {
            em.remove(managedExercise); // Remove the managed entity
        } else {
            throw new IllegalArgumentException("Exercise with ID " + exercise.getId() + " not found, cannot remove.");
        }
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
    
    public void emptyExercises() {
        exercises.clear();
    }

    public List<Exercise> findByNameIn(List<String> names) {
        log.info("trying to find the machines {}", names);
        List<Exercise> findingExercises = new ArrayList<>();
        for (Exercise exercise : exercises) {
            if (names.contains(exercise.getName())) {
                findingExercises.add(exercise);
            }
        }
        return findingExercises;
    }

}
