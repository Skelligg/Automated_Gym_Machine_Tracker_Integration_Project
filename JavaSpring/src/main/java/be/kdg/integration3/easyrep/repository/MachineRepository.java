package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.MaintenanceAlarm;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MachineRepository {

    private static List<Machine> machines = new ArrayList<Machine>();

    public MachineRepository() {
//        machines.add(new Machine(1, "Machine Row", new MaintenanceAlarm(), "static/images/machinesPics/MachineRow.mp4"));
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

}
