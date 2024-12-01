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
    public Gym addGym(Gym gym) {
        return gymRepository.save(gym);
    }

    @Override
    public List<Gym> getGyms() {
        logger.debug("Getting gyms");
        return gymRepository.getGyms();
    }

    @Override
    public Gym findGymByQrCode(String qrCode) {
        return gymRepository.findById(qrCode);
    }

    @Override
    public void emptyRepository() {
        logger.debug("Cleaning the repository");
        gymRepository.emptyList();
    }
}
