package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Machine;

import java.util.List;

public interface MachineService {

    Machine createMachine(Machine machine);
    List<Machine> getAllMachines();
    void emptyRoutines();
}
