package be.kdg.integration3.easyrep.service.routines;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.sessions.MachineSet;

import java.util.List;

public interface ExerciseService {

    Machine createMachine(Machine machine);
    List<Machine> getAllMachines();
    void addMachine(String name, String imageAddress, MachineSet machineSetId, Arduino arduino);
    void emptyMachines();
    List<Machine> findMachinesByNames(List<String> names);
    Machine findMachineById(int id);
}
