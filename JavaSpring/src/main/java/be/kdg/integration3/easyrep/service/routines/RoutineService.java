package be.kdg.integration3.easyrep.service;


import be.kdg.integration3.easyrep.model.Routine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoutineService {

    Routine createRoutine(Routine routine);

    List<Routine> getAllRoutines();

    void emptyRoutines();

}
