package be.kdg.integration3.easyrep.repository.routines;

import be.kdg.integration3.easyrep.model.Machine;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MachineRepository {

    private static List<Machine> machines = new ArrayList<Machine>();

    public MachineRepository() {
        machines.add(new Machine(1, "row machine", "/static/images/machinesPics/MachineRow.mp4"));
        machines.add(new Machine(2, "bench press", "/static/images/machinesPics/MachineRow.mp4"));
        machines.add(new Machine(3, "squat machine", "/static/images/machinesPics/MachineRow.mp4"));
        machines.add(new Machine(4, "unilateral jerk", "/static/images/machinesPics/MachineRow.mp4"));
    }

    public Machine createMachine(Machine machine){
        machines.add(machine);
        return machine;
    }

    public List<Machine> getMachines() {
        return machines;
    }
    
    public void emptyMachines() {
        machines.clear();
    }

    public List<Machine> findByNameIn(List<String> names) {
        List<Machine> machines = new ArrayList<>();
        for (Machine machine : machines) {
            if (names.contains(machine.getName())) {
                machines.add(machine);
            }
        }
        return machines;
    }

}
