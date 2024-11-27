package be.kdg.integration3.easyrep.model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }
}