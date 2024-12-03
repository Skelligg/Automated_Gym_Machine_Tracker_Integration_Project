package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;

import java.util.List;

public interface SetRepository {

    List<ExerciseSet> findAllExerciseSet();
    ExerciseSet findExerciseSetById(int id);
    ExerciseSet createExerciseSet(ExerciseSet exerciseSet);
    void updateSet(ExerciseSet exerciseSet);
    void delete(ExerciseSet exerciseSet);
}
