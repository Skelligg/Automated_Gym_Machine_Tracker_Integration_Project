package be.kdg.integration3.easyrep.presentation.viewModels;

import be.kdg.integration3.easyrep.model.Machine;

import java.util.ArrayList;
import java.util.List;

public class RoutineViewModel {
    private int id;
    private String name;
    private List<Machine> machines = new ArrayList<Machine>();

    public RoutineViewModel() {}

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
