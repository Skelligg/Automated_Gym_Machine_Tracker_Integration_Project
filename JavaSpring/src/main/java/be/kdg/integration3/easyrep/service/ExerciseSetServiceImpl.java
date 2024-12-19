package be.kdg.integration3.easyrep.service;


import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.repository.ExerciseRepository;
import be.kdg.integration3.easyrep.repository.ExerciseSetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExerciseSetServiceImpl implements ExerciseSetService {
    private final ExerciseRepository exerciseRepository;
    private Logger logger = LoggerFactory.getLogger(ExerciseSetServiceImpl.class);
    private ExerciseSetRepository exerciseSetRepository;


    public ExerciseSetServiceImpl(ExerciseSetRepository exerciseSetRepository, ExerciseRepository exerciseRepository) {
        this.exerciseSetRepository = exerciseSetRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<ExerciseSet> findAllExerciseSet() {
        logger.info("Finding all exercise sets");
        return exerciseSetRepository.findAll();
    }

    @Override
    public ExerciseSet findExerciseSetById(int id) {
        logger.info("Finding exercise set by id: {}", id);
        return exerciseSetRepository.findById(id).orElseThrow(() -> new RuntimeException("No exercise set found with id: " + id));
    }

    @Override
    public List<ExerciseSet> findExerciseSetsByExercise(Exercise exercise) {
        return exerciseSetRepository.findExerciseByExercise(exercise);
    }

    @Override
    public ExerciseSet createExerciseSet(ExerciseSet exerciseSet) {
        logger.debug("Creating new exercise set {}", exerciseSet);
        return exerciseSetRepository.save(exerciseSet);
    }

    @Override
    public LocalTime stringToLocalTime(String time) {
        try {
            // Parse the time as an integer (assumes time is in seconds)
            int seconds = Integer.parseInt(time);

            // Convert seconds to LocalTime
            return LocalTime.of(0, 0, seconds);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid time format: " + time + ". Expected an integer in seconds.", e);
        }
    }

    @Override
    public void delete(int id) {
        logger.debug("Deleting exercise set with id {}", id);
        exerciseSetRepository.deleteById(id);
    }

    @Override
    public ExerciseSet update(ExerciseSet exerciseSet) {
        logger.debug("Updating exercise set {}", exerciseSet);

        if (!exerciseSetRepository.existsById(exerciseSet.getSetId())){
            throw new RuntimeException("No exercise set found with id: " + exerciseSet.getSetId());
        }

        return exerciseSetRepository.save(exerciseSet);
    }

    // getting the name of the exercise and weight for the specific session
    @Override
    public List<Object[]> getExerciseSetsNameAndWeightBySessionId(int sessionId) {
        return exerciseSetRepository.findExerciseSetsByNameAndWeightCountBySessionId(sessionId);
    }

    @Override
    public List<ExerciseSet> getProgressForSpecificUser(int gymGoerId, int machineId) {
        List<Exercise> exercises  = exerciseRepository.findExerciseByUserAndMachine(gymGoerId, machineId);

        return exercises.stream().flatMap(e->e.getExerciseSets().stream()).collect(Collectors.toList());
    }

//    @Override
//    public List<ExerciseSet> getProgressForSpecificUser(int gymGoerId, int machineId) {
//        logger.info("Fetching progress for gymGoerId: {} and machineId: {}", gymGoerId, machineId);
//        return exerciseSetRepository.findProgressForSpecificUser(gymGoerId, machineId);
//    }

    @Override
    public List<Map<String, Object>> getWeightData(int gymGoerId,int machineId) {
        return exerciseSetRepository.findWeightsData(machineId,gymGoerId);
    }

    @Override
    public List<Map<String, Object>> getVolumeData(int gymGoerId,int machineId) {
        return exerciseSetRepository.findVolumeData(machineId,gymGoerId);
    }

    @Override
    public List<Map<String, Object>> getRepetitionData(int gymGoerId,int machineId) {
        return exerciseSetRepository.findRepetitionData(machineId,gymGoerId);
    }

    @Override
    public double convertWeightToKilograms(double weightInGrams) {
        // Convert grams to kilograms
        double weightInKilograms = weightInGrams / 1000.0;

        // Round to the nearest 0.25
        weightInKilograms = Math.round(weightInKilograms * 4) / 4.0;

        return weightInKilograms;
    }

}