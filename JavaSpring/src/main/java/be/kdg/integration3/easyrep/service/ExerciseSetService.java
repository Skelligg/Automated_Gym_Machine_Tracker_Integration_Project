package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;

import java.util.List;

public interface ExerciseSetService {

//    ExerciseSet addSet(int setNumber, String setTime, int repCount, double weightCount);

    List<ExerciseSet> findAllExerciseSet();
    ExerciseSet findExerciseSetById(int id);
    ExerciseSet createExerciseSet(ExerciseSet exerciseSet);
    void delete(int id);
    void update(ExerciseSet exerciseSet);
//    List<ExerciseSet> getAllExerciseSets();

}
