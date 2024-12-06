package be.kdg.integration3.easyrep.service.dataProcessors;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.repository.ExerciseSetRepository;
import org.springframework.stereotype.Component;

@Component
public class SaveDataProcessor implements DataProcessor {

    ExerciseSetRepository exerciseSetRepository;

    public SaveDataProcessor(ExerciseSetRepository exerciseSetRepository) {
        this.exerciseSetRepository = exerciseSetRepository;
    }

    @Override
    public void process(ExerciseSet exerciseSet) {
//        exerciseSetRepository.createExerciseSet(exerciseSet);
        exerciseSetRepository.save(exerciseSet);
    }
}
