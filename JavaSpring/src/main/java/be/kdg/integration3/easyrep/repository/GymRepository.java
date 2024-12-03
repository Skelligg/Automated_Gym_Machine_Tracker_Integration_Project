package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Gym;

import java.util.List;

public interface GymRepository {
    List<Gym> findAllGyms();
    Gym findById(int Id);
    Gym create(Gym gym);
    void delete(Gym gym);
    void update(Gym gym);
}
