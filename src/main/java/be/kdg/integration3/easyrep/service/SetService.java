package be.kdg.integration3.easyrep.service;


import be.kdg.integration3.easyrep.model.sessions.Set;
import be.kdg.integration3.easyrep.repository.SetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class SetService {
    SetRepository setRepository;
    List<Set> sets;


    public SetService(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    public Set createSet(LocalTime startTime, LocalTime endTime, int repCount){
        return setRepository.createSet(new Set(startTime, endTime, repCount));
    }

}
