package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface ExerciseSetService {


    List<ExerciseSet> findAllExerciseSet();
    ExerciseSet findExerciseSetById(int id);
    List<ExerciseSet> findExerciseSetsByExercise(Exercise exercise);
    ExerciseSet createExerciseSet(ExerciseSet exerciseSet);
    LocalTime stringToLocalTime(String time);
//    void delete(int id);
//    void update(ExerciseSet exerciseSet);
//    List<ExerciseSet> getAllExerciseSets();
    void delete(int id);
    ExerciseSet update(ExerciseSet exerciseSet);
    List<Object[]> getExerciseSetsNameAndWeightBySessionId(int sessionId);
    List<ExerciseSet> getProgressForSpecificUser(int gymGoerId, int machineId);
    List<Map<String, Object>> getWeightData(int gymGoerId,int machineId);
    List<Map<String, Object>> getVolumeData(int gymGoerId,int machineId);
    List<Map<String, Object>> getRepetitionData(int gymGoerId,int machineId);

}
