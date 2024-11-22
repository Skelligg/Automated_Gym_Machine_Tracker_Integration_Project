package be.kdg.integration3.easyrep.service;


import be.kdg.integration3.easyrep.model.Routine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoutineService {

    public Routine createRoutine(Routine routine);

    public List<Routine> getAllRoutines();

    public void emptyRoutines();

}
