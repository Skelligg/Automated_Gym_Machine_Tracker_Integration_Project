package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Integer> {



    void delete(ExerciseSet exerciseSet);

    @Query("Select e from ExerciseSet e")
    List<ExerciseSet> findAllExerciseSets();
//    @Query("select e from ExerciseSet e where e.field=?1")
//    List<ExerciseSet> findByExample(ExerciseSet exerciseSet);
    //Ref Repetition Repository from the set service
}
