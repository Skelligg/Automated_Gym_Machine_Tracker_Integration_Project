package be.kdg.integration3.easyrep.service.session;

import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.repository.ExerciseRepository;
import be.kdg.integration3.easyrep.service.routines.RoutineServiceImpl;
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
        logger.info("Creating exercise " + exercise);
        return exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> getAllExercises() {

        return exerciseRepository.findAll();
    }

    @Override
    public Exercise getExerciseById (int id){
        return exerciseRepository.findByexerciseId(id);
    }

    public Exercise findByName(String name){
        return exerciseRepository.findByexerciseName(name);
    }

    @Override
    public Exercise getExercise(int id) {
        return null;
    }


    @Override
    public void deleteExercise(int id) {

    }

    @Override
    public void updateExercise(Exercise exercise) {

    }
//
//    @Override
//    public Exercise findExerciseByNames(String name) {
//        return f
//    }


//    //I don't understand why ExerciseSet is in Exercise so i create the add Exercise without it
//    public void addExercise(String name, String imageAddress, Arduino arduino){
//        Exercise exercise = new Exercise(name);
//        logger.info("Creating a exercise {}", exercise);
//        exerciseRepository.createExercise(exercise);
//    }







}
