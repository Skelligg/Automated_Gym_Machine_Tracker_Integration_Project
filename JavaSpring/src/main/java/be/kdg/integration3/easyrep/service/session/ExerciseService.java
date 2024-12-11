package be.kdg.integration3.easyrep.service.session;

import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.Session;

import java.util.List;
import java.util.Map;

public interface ExerciseService {

    Exercise getExerciseById (int id);
    Exercise createExercise(Exercise exercise);
    List<Exercise> getAllExercises();
    Exercise getExercise(int id);
    void deleteExercise(int id);
    void updateExercise(Exercise exercise);
//    List<Exercise> findExerciseByNames(List<String> names);
    Exercise findByName(String name);
    Exercise findExerciseBySessionAndName(Session session, String exerciseName);
//    List<Map<String, Object>> getChartData(String name);
}
