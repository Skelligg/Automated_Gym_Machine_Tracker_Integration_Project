package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;

import java.util.List;

public interface MachineSetService {
    //    Set addSet(LocalTime startTime, LocalTime endTime, int repCount);
    ExerciseSet addSet(int setNumber, String setTime, int repCount, double weightCount);

    List<ExerciseSet> getSets();

    void emptyRepository();

}
