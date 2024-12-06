package be.kdg.integration3.easyrep.service.routines;

import be.kdg.integration3.easyrep.model.RoutineExercise;
import be.kdg.integration3.easyrep.model.sessions.Exercise;

import java.util.List;

public interface RoutineExerciseService {

    RoutineExercise createExercise(RoutineExercise exercise);
    List<RoutineExercise> getAllExercises();
    void deleteExercise(int id);
    void updateExercise(RoutineExercise exercise);
    RoutineExercise findByName(String name);
}
