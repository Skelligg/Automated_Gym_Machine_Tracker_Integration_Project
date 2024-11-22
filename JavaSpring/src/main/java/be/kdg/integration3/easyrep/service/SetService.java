package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.sessions.Set;

import java.util.List;

public interface SetService {
    //    Set addSet(LocalTime startTime, LocalTime endTime, int repCount);
    Set addSet(int setNumber, String setTime, int repCount);

    List<Set> getSets();

    void emptyRepository();

}
