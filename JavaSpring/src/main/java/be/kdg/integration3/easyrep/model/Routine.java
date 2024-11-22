package be.kdg.integration3.easyrep.model;

import be.kdg.integration3.easyrep.model.sessions.RoutineSession;

import java.util.ArrayList;
import java.util.List;

public class Routine {
    private int id;
    private String name;
    private List<Machine> machines = new ArrayList<>();

    public Routine(int id, String name, List<Machine> machines) {
        this.id = id;
        this.name = name;
        this.machines = machines;
    }

    public Routine() {
    }

}