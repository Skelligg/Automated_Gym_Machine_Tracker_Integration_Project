package be.kdg.integration3.easyrep.service.routines;

import be.kdg.integration3.easyrep.model.RoutineExercise;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.repository.routines.RoutineExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineExerciseServiceImpl implements RoutineExerciseService {
    private RoutineExerciseRepository routineExerciseRepository;

    public RoutineExerciseServiceImpl(RoutineExerciseRepository routineExerciseRepository) {
        this.routineExerciseRepository = routineExerciseRepository;
    }

    @Override
    public RoutineExercise createExercise(RoutineExercise exercise) {
        return routineExerciseRepository.save(exercise);
    }

    @Override
    public List<RoutineExercise> getAllExercises() {
        return routineExerciseRepository.findAll();
    }

//    @Override
//    public RoutineExercise getExercise(int id) {
//        return routineExerciseRepository.findById(id);
//    }

    @Override
    public void deleteExercise(int id) {

    }

    @Override
    public void updateExercise(RoutineExercise exercise) {

    }

    @Override
    public RoutineExercise findByName(String name) {
        return null;
    }
}
