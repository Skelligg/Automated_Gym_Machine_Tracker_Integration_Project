package be.kdg.integration3.easyrep.service;


import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.repository.SetRepository;
import be.kdg.integration3.easyrep.repository.SetRepositoryImpl;
import be.kdg.integration3.easyrep.repository.StatisticsRepositoryImpl;
import be.kdg.integration3.easyrep.service.dataProcessors.DataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseSetServiceImpl implements ExerciseSetService {
    private Logger logger = LoggerFactory.getLogger(ExerciseSetServiceImpl.class);
    private SetRepository setRepository;
    private DataProcessor dataProcessor;

    public ExerciseSetServiceImpl(@Qualifier("compositeExerciseSet") DataProcessor dataProcessor) {
        logger.debug("Initializing Set Repository");
        this.dataProcessor = dataProcessor;
        this.setRepository = new SetRepositoryImpl();
    }

//    @Override
//    public Set addSet(LocalTime startTime, LocalTime endTime, int repCount){
//        return setRepository.createSet(new Set(startTime, endTime, repCount));
//    }

    @Override
    public ExerciseSet addSet(int setNumber, String setTime, int repCount, double weightCount){
        ExerciseSet tempExerciseSet = new ExerciseSet(setNumber, setTime, repCount, weightCount);
         //dataProcessor.process(tempExerciseSet);
        setRepository.createSet(tempExerciseSet);
         return tempExerciseSet;
    }

    @Override
    public List<ExerciseSet> getSets(){
        logger.debug("Getting sets");
        return setRepository.getSets();
    }

    @Override
    public void emptyRepository(){
        logger.debug("Cleaning the repository");
        setRepository.emptyList();
    }

    @Override
    public List<ExerciseSet> getAllExerciseSets() {
        return StatisticsRepositoryImpl.createDummyData();
    }

}
