package be.kdg.integration3.easyrep.repository.routines;


import be.kdg.integration3.easyrep.model.Routine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoutineRepository {

    private static final Logger log = LogManager.getLogger(RoutineRepository.class);
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

    public Routine getRoutineByName(String routineName) {
        return routines.stream()
                .filter(routine -> routine.getName().toLowerCase().contains(routineName.toLowerCase())) // Case-insensitive partial match
                .findFirst() // Find the first match
                .orElse(null); // Return null if no match is found
    }
}
