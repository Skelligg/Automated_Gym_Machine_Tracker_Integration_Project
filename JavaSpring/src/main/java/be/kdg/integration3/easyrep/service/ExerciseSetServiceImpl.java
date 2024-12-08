package be.kdg.integration3.easyrep.service;


import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.repository.SetRepository;
import be.kdg.integration3.easyrep.service.dataProcessors.DataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class ExerciseSetServiceImpl implements ExerciseSetService {
    private Logger logger = LoggerFactory.getLogger(ExerciseSetServiceImpl.class);
    private SetRepository setRepository;
    private DataProcessor dataProcessor;

    public ExerciseSetServiceImpl(@Qualifier("compositeExerciseSet") DataProcessor dataProcessor, SetRepository setRepository) {
        logger.debug("Initializing Set Repository");
        this.dataProcessor = dataProcessor;
        this.setRepository = setRepository;
    }

//    @Override
//    public Set addSet(LocalTime startTime, LocalTime endTime, int repCount){
//        return setRepository.createSet(new Set(startTime, endTime, repCount));
//    }

//    @Override
//    public ExerciseSet addSet(int setNumber, String setTime, int repCount, double weightCount){
////        ExerciseSet tempExerciseSet = new ExerciseSet(setNumber, setTime, repCount, weightCount);
//         dataProcessor.process(tempExerciseSet);
//         return tempExerciseSet;
//    }

    @Override
    public List<ExerciseSet> findAllExerciseSet() {
        return setRepository.findAll();
    }

    @Override
    public List<ExerciseSet> findExerciseSetsByExercise(Exercise exercise) {
        return setRepository.findExerciseSetsByExercise(exercise);
    }

    @Override
    public ExerciseSet createExerciseSet(ExerciseSet exerciseSet) {
        return setRepository.save(exerciseSet);
    }

    @Override
    public LocalTime stringToLocalTime(String time) {
        return LocalTime.parse(time);
    }

//    @Override
//    public void delete(int id) {
//        logger.debug("Deleting exercise set with id {}", id);
//        ExerciseSet exerciseSet = setRepository.findExerciseSetById(id);
//        setRepository.delete(exerciseSet);
//    }

//    @Override
//    public void update(ExerciseSet exerciseSet) {
//        setRepository.updateSet(exerciseSet);
//    }
}
