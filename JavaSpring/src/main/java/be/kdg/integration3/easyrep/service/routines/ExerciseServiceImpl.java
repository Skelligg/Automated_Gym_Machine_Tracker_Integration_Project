package be.kdg.integration3.easyrep.service.routines;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.repository.routines.ExerciseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    Logger logger = LoggerFactory.getLogger(RoutineServiceImpl.class);
    private ExerciseRepository exerciseRepository;


    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Exercise createExercise(Exercise exercise) {
        logger.info("Creating a exercise {}", exercise);
        exerciseRepository.createExercise(exercise);
        return exercise;
    }

    public void addExercise(String name){
        Exercise exercise = new Exercise(name);
        logger.info("Creating a exercise {}", exercise);
        exerciseRepository.createExercise(exercise);
    }

//    //I don't understand why ExerciseSet is in Exercise so i create the add Exercise without it
//    public void addExercise(String name, String imageAddress, Arduino arduino){
//        Exercise exercise = new Exercise(name);
//        logger.info("Creating a exercise {}", exercise);
//        exerciseRepository.createExercise(exercise);
//    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.getExercises();
    }

    @Override
    public void emptyExercises() {
        exerciseRepository.emptyExercises();
    }

    @Override
    public List<Exercise> findExercisesByNames(List<String> names) {
        return exerciseRepository.findByNameIn(names); // Assuming JPA repository
    }





}
