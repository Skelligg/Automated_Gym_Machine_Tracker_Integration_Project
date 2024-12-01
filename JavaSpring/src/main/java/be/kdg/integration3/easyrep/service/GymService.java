package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Gym;
import be.kdg.integration3.easyrep.model.sessions.MachineSet;

import java.util.List;

public interface GymService {
    Gym addGym(Gym gym);
    List<Gym> getGyms();
    Gym findGymByQrCode(String qrCode);
    void emptyRepository();
}
