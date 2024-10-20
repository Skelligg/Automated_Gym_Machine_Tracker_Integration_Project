package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;

import java.util.List;

public interface SetRepository {
    MachineSet createSet(MachineSet machineSet);

    List<MachineSet> getSets();

    void emptyList();
}
