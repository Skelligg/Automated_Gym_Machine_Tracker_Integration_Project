package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompositeMachineSet implements DataProcessor{


    List<DataProcessor> processors;

    @Override
    public void process(MachineSet machineSet) {
        processors.forEach(p -> p.process(machineSet));
    }
}
