package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Gym;
import be.kdg.integration3.easyrep.repository.GymRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class GymServiceImpl implements GymService {
    private Logger logger = LoggerFactory.getLogger(GymServiceImpl.class);
    private GymRepository gymRepository;

    public GymServiceImpl(GymRepository gymRepository) {
        logger.debug("Initializing Set Repository");
        this.gymRepository = gymRepository;
    }

    @Override
    public Gym findGymByQrCode(int qrCode) {
        return gymRepository.findByGymId(qrCode);
    }

    @Override
    public List<Gym> findAllGyms() {
        logger.debug("Finding All Gyms");
        return gymRepository.findAll();
    }

    @Override
    public Gym findGymById(int id) {
        logger.debug("Find Gym by id: {}", id);
        return gymRepository.findByGymId(id);
    }

    @Override
    public Gym createGym(Gym gym) {
        logger.debug("Creating Gym: {}", gym);
        return gymRepository.save(gym);
    }

    @Override
    public void updateGym(Gym gym) {
        logger.debug("Updating Gym: {}", gym);
        gymRepository.save(gym);
    }

    @Override
    public void delete(int id) {
        logger.debug("Deleting Gym: {}", id);
        Gym gym = gymRepository.findByGymId(id);
        gymRepository.delete(gym);
    }

    @Override
    public TreeMap<String, Integer> findUsageOfGymPerHour(int gymId) {
        logger.debug("Finding Usage of Gym: {}", gymId);
        List<Object[]> result = gymRepository.findUsageDuringSpecificDayOfTheWeek(gymId);

        //list of hours that will be show in the graphic
        TreeMap<String, Integer> gymUsageStart = new TreeMap<>();
        TreeMap<String, Integer> gymUsageEnd = new TreeMap<>();
        int openHour = 7;
        int closedHour = 22;
        for (int hour = openHour; hour < closedHour; hour++) {
            gymUsageStart.put(String.format("%02d:00", hour), 0);
            gymUsageEnd.put(String.format("%02d:00", hour), 0);
        }

        for(Object[] row : result){
            LocalDateTime startTime = (LocalDateTime) row[0];
            LocalDateTime endTime = (LocalDateTime) row[1];
            int start =  startTime.getHour();
            int end =  endTime.getHour();

            gymUsageStart.put(String.format("%02d:00", start), gymUsageStart.getOrDefault(String.format("%02d:00", start),0) + 1);
            gymUsageEnd.put(String.format("%02d:00", end), gymUsageEnd.getOrDefault(String.format("%02d:00", end),0) + 1);
        }

        return gymUsageStart;
    }

    @Override
    public TreeMap<Integer, Integer> findMonthlyUsageOfGymPerDay(int gymId) {
            logger.debug("Finding Usage of Gym: {}", gymId);
            TreeMap<Integer, Integer> gymUsage = new TreeMap<>();
            List<Object[]> result = gymRepository.findGymGoersFromTheGym(gymId);

            for(Object[] row : result){
                long date = Long.parseLong((String) row[0]);
                Long usage = (Long) row[1];
                gymUsage.put((int) date, usage.intValue());
            }
            // this will have to be 12 because otherwise will be showing just one month for january
            LocalDate dateNow = LocalDate.now();
            int monthNow = dateNow.getMonthValue();
            monthNow = 12;
            for (int i = 1; i <= monthNow; i++) {
                if (!gymUsage.containsKey(i)) {
                    gymUsage.put(i, 0);
                }
            }

        return gymUsage;
    }


}
