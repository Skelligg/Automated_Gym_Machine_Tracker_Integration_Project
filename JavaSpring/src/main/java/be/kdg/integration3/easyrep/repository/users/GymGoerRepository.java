package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.GymGoer;

import java.util.List;

public interface GymGoerRepository {
    void save(GymGoer gymGoer);

    GymGoer findByUserId(int userId);

    List<GymGoer> findAll();
}
