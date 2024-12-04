package be.kdg.integration3.easyrep.service.dataProcessors;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.repository.SetRepository;
import org.springframework.stereotype.Component;

@Component
public class SaveDataProcessor implements DataProcessor {

    SetRepository setRepository;

    public SaveDataProcessor(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    @Override
    public void process(ExerciseSet exerciseSet) {
        setRepository.createSet(exerciseSet);
    }
}
