package be.kdg.integration3.easyrep.repository.routines;

import be.kdg.integration3.easyrep.model.RoutineExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoutineExerciseRepository extends JpaRepository <RoutineExercise, Integer> {

}
