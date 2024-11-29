package be.kdg.integration3.easyrep.service;


import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import be.kdg.integration3.easyrep.repository.SetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineSetServiceImpl implements MachineSetService {
    private Logger logger = LoggerFactory.getLogger(MachineSetServiceImpl.class);
    private SetRepository setRepository;
    private DataProcessor dataProcessor;

    public MachineSetServiceImpl(DataProcessor dataProcessor) {
        logger.debug("Initializing Set Repository");
        this.dataProcessor = dataProcessor;
    }

//    @Override
//    public Set addSet(LocalTime startTime, LocalTime endTime, int repCount){
//        return setRepository.createSet(new Set(startTime, endTime, repCount));
//    }

    @Override
    public MachineSet addSet(int setNumber, String setTime, int repCount){
        MachineSet tempMachineSet = new MachineSet(setNumber, setTime, repCount);
         dataProcessor.process(tempMachineSet);
         return tempMachineSet;
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
