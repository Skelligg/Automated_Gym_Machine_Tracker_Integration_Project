package be.kdg.integration3.easyrep.domain;

import be.kdg.integration3.easyrep.domain.sessions.RoutineSession;

import java.util.ArrayList;
import java.util.List;

public class Routine {
    private long id;
    private String name;
    private List<Machine> machines = new ArrayList<>();
    private List<RoutineSession> routineSessions = new ArrayList<>();

    public void beginNewSession() {

    }

    public void endCurrentSession() {

    }
}