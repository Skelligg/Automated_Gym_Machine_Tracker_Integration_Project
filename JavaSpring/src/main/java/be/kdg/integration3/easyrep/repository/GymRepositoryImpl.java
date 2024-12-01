package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Gym;
import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class GymRepositoryImpl implements GymRepository {
    private static List<Gym> gyms = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Gym save(Gym gym) {
        gyms.add(gym);
        return gym;
    }

    @Override
    public List<Gym> getGyms(){
        return gyms;
    }

    @Override
    public Gym findById(String id) {
        logger.info(gyms.get(0).getName());
        int gymId = Integer.parseInt(id.trim());
        for (Gym gym : gyms) {
            if (gymId == gym.getGymId()) {
                return gym; // Match found
            }
        }
        return null;
    }

    @Override
    public void emptyList(){
        gyms.clear();
    }
}
