package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Integer> {



    void delete(ExerciseSet exerciseSet);

    @Query("Select e from ExerciseSet e")
    List<ExerciseSet> findAllExerciseSets();

    List<ExerciseSet> findExerciseByExercise(Exercise exercise);

    @Query("select e.exerciseName,es.weightCount, e.machine.machineId from ExerciseSet es join es.exercise e where e.session.session_id = :sessionId")
    List<Object[]> findExerciseSetsByNameAndWeightCountBySessionId(@Param("sessionId") int sessionId);


    //query to find the past sets for user and specific exercise
    @Query("select es from ExerciseSet es join es.exercise e join e.machine m join e.session s join s.gymGoerId g where g.userId = :gymGoerId and m.machineId = :machineId")
        List<ExerciseSet> findProgressForSpecificUser(@Param("machineId") int machineId, @Param("gymGoerId") int gymGoerId);

    //Getting all the data for the weights
    @Query("select e.session.startSession as date, es.weightCount as weightCount from ExerciseSet es join Exercise e on (e.exerciseId = es.exercise.exerciseId) where e.session.gymGoerId.userId = :gymGoerId and es.exercise.machine.machineId = :machineId order by es.startTime")
    List<Map<String,Object>> findWeightsData(@Param("machineId") int machineId, @Param("gymGoerId") int gymGoerId);

    //Getting all the data for the volume
    @Query("select e.session.startSession as date, sum(es.weightCount * es.repetitionCount) as volumeD  from ExerciseSet es join Exercise e on (e.exerciseId = es.exercise.exerciseId) where e.session.gymGoerId.userId = :gymGoerId and es.exercise.machine.machineId = :machineId  group by e.session.startSession order by e.session.startSession")
    List<Map<String,Object>> findVolumeData(@Param("machineId") int machineId, @Param("gymGoerId") int gymGoerId);

    //getting all the data for the repetitions
    @Query("select e.session.startSession as date, sum(es.repetitionCount) as repCount  from ExerciseSet es join Exercise e on (e.exerciseId = es.exercise.exerciseId) where e.session.gymGoerId.userId = :gymGoerId and es.exercise.machine.machineId = :machineId  group by e.session.startSession order by e.session.startSession")
    List<Map<String,Object>> findRepetitionData(@Param("machineId") int machineId, @Param("gymGoerId") int gymGoerId);

}
