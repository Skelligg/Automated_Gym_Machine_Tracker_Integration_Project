package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.ExerciseList;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.repository.ExerciseListRepository;
import be.kdg.integration3.easyrep.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseListService {
    private ExerciseListRepository exerciseListRepository;

    public ExerciseListService(ExerciseListRepository exerciseListRepository) {
        this.exerciseListRepository = exerciseListRepository;
    }

    public List<ExerciseList> getAllExercises() {
        return exerciseListRepository.findAll();
    }

    public ExerciseList getExerciseByName(String name) {
        return exerciseListRepository.getExerciseListByName(name);
    }

    public void addExercise(ExerciseList exerciseList) {
        exerciseListRepository.save(exerciseList);
    }
}
