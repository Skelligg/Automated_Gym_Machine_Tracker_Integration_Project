package be.kdg.integration3.easyrep.service.dataProcessors;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompositeMachineSet implements DataProcessor{


    List<DataProcessor> processors;

    @Override
    public void process(ExerciseSet exerciseSet) {
        processors.forEach(p -> p.process(exerciseSet));
    }
}
