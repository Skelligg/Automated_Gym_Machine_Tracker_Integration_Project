package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;

import java.util.List;

public interface SetService {
//    Set addSet(LocalTime startTime, LocalTime endTime, int repCount);
    MachineSet addSet(int repCount);

    List<MachineSet> getSets();
}
