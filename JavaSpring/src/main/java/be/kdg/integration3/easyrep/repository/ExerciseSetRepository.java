package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;

@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Integer> {



    void delete(ExerciseSet exerciseSet);

    @Query("Select e from ExerciseSet e")
    List<ExerciseSet> findAllExerciseSets();


    @Query("select es.weightCount, e.exerciseName from ExerciseSet es join es.exercise e where e.session.session_id = :sessionId")
    List<Object[]> findExerciseSetsByNameAndWeightCountBySessionId(@Param("sessionId") int sessionId);

}
