package be.kdg.integration3.easyrep.service.routines;


import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.Routine;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoutineService {

    @Transactional
    void removeRoutineById(int id);

    void createRoutine(Routine routine);

    List<Routine> getAllRoutines();
    Routine getRoutine(int id);

    void removeRoutine(int id);
    Routine getRoutineByName(String name);

    public List<Routine> getRoutinesByGymGoer(GymGoer gymgoer);
}
