package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;

import java.util.List;

public interface ExerciseSetService {


    List<ExerciseSet> findAllExerciseSet();
    ExerciseSet findExerciseSetById(int id);
    ExerciseSet createExerciseSet(ExerciseSet exerciseSet);
    void delete(int id);
    ExerciseSet update(ExerciseSet exerciseSet);

}
