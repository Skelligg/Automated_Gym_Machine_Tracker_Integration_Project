package be.kdg.integration3.easyrep.service.routines;

import be.kdg.integration3.easyrep.model.Gender;
import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.repository.routines.RoutineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineServiceImpl implements RoutineService {

    Logger logger = LoggerFactory.getLogger(RoutineServiceImpl.class);
    private RoutineRepository routineRepository;


    @Autowired
    public RoutineServiceImpl(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    @Override
    public void createRoutine(Routine routine) {
        logger.info("Creating Routine");
        routineRepository.createRoutine(routine);
    }

    @Override
    public List<Routine> getAllRoutines() {
        logger.info("Getting all Routines");
        return routineRepository.getAllRoutines();
    }

    @Override
    public Routine getRoutine(int id) {
        logger.info("Getting Routine");
        return routineRepository.getRoutine(id);
    }

    @Override
    public void removeRoutine(int id) {
        logger.info("Removing Routine");
        Routine routine = routineRepository.getRoutine(id);
        routineRepository.removeRoutine(routine);
    }

    @Override
    public Routine getRoutineByName(String name) {
        logger.info("Getting Routine by name");
        return routineRepository.getRoutineByName(name);
    }

    @Override
    public void updateRoutineExercise(Routine routine) {
        logger.info("Updating Routine");
        routineRepository.updateRoutineExercises(routine);
    }
}
