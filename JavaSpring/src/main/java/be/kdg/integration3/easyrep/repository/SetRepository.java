package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SetRepository extends JpaRepository<ExerciseSet, Integer> {


    List<ExerciseSet> findExerciseSetsByExercise(Exercise exercise);

    //    List<ExerciseSet> findAllExerciseSet();
//    ExerciseSet findExerciseSetById(int id);
//    ExerciseSet createExerciseSet(ExerciseSet exerciseSet);
//    void updateSet(ExerciseSet exerciseSet);
//    void delete(ExerciseSet exerciseSet);
}
