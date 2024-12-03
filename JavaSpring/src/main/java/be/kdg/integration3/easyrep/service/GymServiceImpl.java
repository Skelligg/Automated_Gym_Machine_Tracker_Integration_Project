package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Gym;
import be.kdg.integration3.easyrep.repository.GymRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymServiceImpl implements GymService {
    private Logger logger = LoggerFactory.getLogger(GymServiceImpl.class);
    private GymRepository gymRepository;

    public GymServiceImpl(GymRepository gymRepository) {
        logger.debug("Initializing Set Repository");
        this.gymRepository = gymRepository;
    }

    @Override
    public Gym findGymByQrCode(String qrCode) {
        return null;
    }

    @Override
    public List<Gym> findAllGyms() {
        logger.debug("Finding All Gyms");
        return gymRepository.findAllGyms();
    }

    @Override
    public Gym findGymById(int id) {
        logger.debug("Find Gym by id: {}", id);
        return gymRepository.findById(id);
    }

    @Override
    public Gym createGym(Gym gym) {
        logger.debug("Creating Gym: {}", gym);
        return gymRepository.create(gym);
    }

    @Override
    public void updateGym(Gym gym) {
        logger.debug("Updating Gym: {}", gym);
        gymRepository.update(gym);
    }

    @Override
    public void delete(int id) {
        logger.debug("Deleting Gym: {}", id);
        Gym gym = gymRepository.findById(id);
        gymRepository.delete(gym);
    }

}
