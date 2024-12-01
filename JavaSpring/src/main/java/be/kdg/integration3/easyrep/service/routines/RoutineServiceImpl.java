package be.kdg.integration3.easyrep.service.routines;

import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.repository.routines.RoutineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineServiceImpl implements RoutineService {

    Logger logger = LoggerFactory.getLogger(RoutineServiceImpl.class);
    private RoutineRepository routineRepository;


    public RoutineServiceImpl(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    @Override
    public Routine createRoutine(Routine routine) {
        routineRepository.createRoutine(routine);
        return routine;
    }

    @Override
    public List<Routine> getAllRoutines() {
        return routineRepository.getAllRoutines();
    }

    @Override
    public void emptyRoutines() {
        routineRepository.emptyRoutines();
    }


    @Override
    public Routine getRoutineByName(String name) {
        logger.info("in service getting routine repo");
        return routineRepository.getRoutineByName(name);
    }
}
