package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;

import java.time.LocalDateTime;
import java.util.List;

public interface MachineSetService {
    //    Set addSet(LocalTime startTime, LocalTime endTime, int repCount);
    MachineSet addSet( LocalDateTime startTime, LocalDateTime endTime,double weightCount );

    List<MachineSet> getSets();

    void emptyRepository();

}
