package be.kdg.integration3.easyrep.service.routines;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.model.sessions.Exercise;

import java.util.List;

public interface ExerciseService {

    Exercise createExercise(Exercise machine);

    List<Exercise> getAllExercises();

    void addExercise(String name);

    void emptyExercises();

    List<Exercise> findExercisesByNames(List<String> names);
}
