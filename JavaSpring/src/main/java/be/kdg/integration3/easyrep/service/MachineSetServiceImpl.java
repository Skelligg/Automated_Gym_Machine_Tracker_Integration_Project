package be.kdg.integration3.easyrep.service;


import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import be.kdg.integration3.easyrep.repository.SetRepository;
import be.kdg.integration3.easyrep.repository.routines.MachineSetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MachineSetServiceImpl implements MachineSetService {
    private Logger logger = LoggerFactory.getLogger(MachineSetServiceImpl.class);
    private SetRepository setRepository;
    private DataProcessor dataProcessor;
    private MachineSetRepository machineSetRepository;

    public MachineSetServiceImpl(@Qualifier("compositeMachineSet") DataProcessor dataProcessor) {
        logger.debug("Initializing Set Repository");
        this.dataProcessor = dataProcessor;
    }

//    @Override
//    public Set addSet(LocalTime startTime, LocalTime endTime, int repCount){
//        return setRepository.createSet(new Set(startTime, endTime, repCount));
//    }


    @Override
    public MachineSet addSet(LocalDateTime startTime, LocalDateTime endTime, double weightCount) {
        return null;
    }

    public MachineSet saveMachineSet(MachineSet machineSet) {
        // You can add additional business logic here if needed
        return machineSetRepository.createMachineSet(machineSet);
    }


    @Override
    public List<MachineSet> getSets(){
        logger.debug("Getting sets");
        return setRepository.getSets();
    }

    @Override
    public void emptyRepository(){
        logger.debug("Cleaning the repository");
        setRepository.emptyList();
    }

}
