package be.kdg.integration3.easyrep.service;


import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.repository.SetRepository;
import be.kdg.integration3.easyrep.service.dataProcessors.DataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineSetServiceImpl implements MachineSetService {
    private Logger logger = LoggerFactory.getLogger(MachineSetServiceImpl.class);
    private SetRepository setRepository;
    private DataProcessor dataProcessor;

    public MachineSetServiceImpl(@Qualifier("compositeMachineSet") DataProcessor dataProcessor) {
        logger.debug("Initializing Set Repository");
        this.dataProcessor = dataProcessor;
    }

//    @Override
//    public Set addSet(LocalTime startTime, LocalTime endTime, int repCount){
//        return setRepository.createSet(new Set(startTime, endTime, repCount));
//    }

    @Override
    public ExerciseSet addSet(int setNumber, String setTime, int repCount, double weightCount){
        ExerciseSet tempExerciseSet = new ExerciseSet(setNumber, setTime, repCount, weightCount);
         dataProcessor.process(tempExerciseSet);
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

}
