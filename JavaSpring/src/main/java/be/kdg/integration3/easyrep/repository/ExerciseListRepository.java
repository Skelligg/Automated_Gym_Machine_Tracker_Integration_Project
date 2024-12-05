package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.ExerciseList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseListRepository extends JpaRepository <ExerciseList, Integer>{
    @Override
    List<ExerciseList> findAll();


}
