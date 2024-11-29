package be.kdg.integration3.easyrep.service.dataProcessors;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import be.kdg.integration3.easyrep.repository.routines.MachineSetRepository;
import org.springframework.stereotype.Component;

@Component
public class SaveDataProcessor implements DataProcessor {

    MachineSetRepository machineSetRepository;

    @Override
    public void process(MachineSet machineSet) {
        machineSetRepository.createMachineSet(machineSet);
    }
}
