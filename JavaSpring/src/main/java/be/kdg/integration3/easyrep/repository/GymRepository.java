package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Gym;

import java.util.List;

public interface GymRepository {
    Gym save(Gym gym);
    List<Gym> getGyms();
    Gym findById(String Id);
    void emptyList();
}
