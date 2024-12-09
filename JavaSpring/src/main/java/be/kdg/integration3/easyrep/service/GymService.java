package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Gym;

import java.util.List;

public interface GymService {

    Gym findGymByQrCode(int qrCode);
    List<Gym> findAllGyms();
    Gym findGymById(int id);
    Gym createGym(Gym gym);
    void updateGym(Gym gym);
    void delete(int id);
}
