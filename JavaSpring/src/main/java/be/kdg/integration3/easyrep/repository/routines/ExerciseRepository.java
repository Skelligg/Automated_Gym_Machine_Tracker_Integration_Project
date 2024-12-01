package be.kdg.integration3.easyrep.repository.routines;

import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ExerciseRepository {

    private static final Logger log = LoggerFactory.getLogger(ExerciseRepository.class);
    private static List<Exercise> exercises = new ArrayList<Exercise>();

    public ExerciseRepository() {
        exercises.add(new Exercise("row machine"));
        exercises.add(new Exercise("bench press"));
        exercises.add(new Exercise("squat machine"));
        exercises.add(new Exercise("unilateral jerk"));
    }

    public void createExercise(Exercise exercise){
        exercises.add(exercise);
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
