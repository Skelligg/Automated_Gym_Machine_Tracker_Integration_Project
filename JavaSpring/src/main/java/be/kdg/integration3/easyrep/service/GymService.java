package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Gym;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public interface GymService {

    Gym findGymByQrCode(int qrCode);
    List<Gym> findAllGyms();
    Gym findGymById(int id);
    Gym createGym(Gym gym);
    void updateGym(Gym gym);
    void delete(int id);
    TreeMap<String,Integer> findUsageOfGymPerHour(int gymId);
    TreeMap<Integer,Integer> findMonthlyUsageOfGymPerDay(int gymId);
}
