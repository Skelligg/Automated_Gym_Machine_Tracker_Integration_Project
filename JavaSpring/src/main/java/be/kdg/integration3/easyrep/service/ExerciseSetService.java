package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;

import java.time.LocalTime;
import java.util.List;

public interface ExerciseSetService {

//    ExerciseSet addSet(int setNumber, String setTime, int repCount, double weightCount);

    List<ExerciseSet> findAllExerciseSet();
    List<ExerciseSet> findExerciseSetsByExercise(Exercise exercise);
    ExerciseSet createExerciseSet(ExerciseSet exerciseSet);
    LocalTime stringToLocalTime(String time);
//    void delete(int id);
//    void update(ExerciseSet exerciseSet);
//    List<ExerciseSet> getAllExerciseSets();

}
