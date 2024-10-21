package be.kdg.integration3.easyrep.model.sessions;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.Routine;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoutineSession {
    private long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Routine routine;
    private Machine activeMachine;
    private List<MachineSession> machineSessions = new ArrayList<>();

    public RoutineSession() {
        System.out.println("routine session has begun");
    }
}
