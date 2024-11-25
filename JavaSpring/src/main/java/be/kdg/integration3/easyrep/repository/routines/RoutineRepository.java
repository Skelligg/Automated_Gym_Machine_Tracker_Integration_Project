package be.kdg.integration3.easyrep.repository.routines;


import be.kdg.integration3.easyrep.model.Routine;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoutineRepository {

    private static List<Routine> routines = new ArrayList<>();

    public Routine createRoutine(Routine routine) {
        routines.add(routine);
        return routine;
    }

    public List<Routine> getAllRoutines() {
        return routines;
    }

    public void emptyRoutines() {
        routines.clear();
    }
}
