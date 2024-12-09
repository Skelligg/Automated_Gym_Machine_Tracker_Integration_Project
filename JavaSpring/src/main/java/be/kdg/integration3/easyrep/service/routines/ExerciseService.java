package be.kdg.integration3.easyrep.service.routines;

import be.kdg.integration3.easyrep.model.sessions.Exercise;

import java.util.List;
import java.util.Map;

public interface ExerciseService {

    Exercise createExercise(Exercise exercise);
    List<Exercise> getAllExercises();
    Exercise getExercise(int id);
    void deleteExercise(int id);
    void updateExercise(Exercise exercise);
//    List<Exercise> findExerciseByNames(List<String> names);
    Exercise findByName(String name);
//    List<Map<String, Object>> getChartData(String name);
}
