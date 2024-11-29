package be.kdg.integration3.easyrep.service;


import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import be.kdg.integration3.easyrep.repository.SetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetServiceImpl implements SetService {
    private Logger logger = LoggerFactory.getLogger(SetServiceImpl.class);
    private SetRepository setRepository;


    public SetServiceImpl(SetRepository setRepository) {
        logger.debug("Initializing Set Repository");
        this.setRepository = setRepository;
    }

//    @Override
//    public Set addSet(LocalTime startTime, LocalTime endTime, int repCount){
//        return setRepository.createSet(new Set(startTime, endTime, repCount));
//    }

    @Override
    public MachineSet addSet(int setNumber, String setTime, int repCount, double weight){
        return setRepository.createSet(new MachineSet(setNumber, setTime, repCount, weight));
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
