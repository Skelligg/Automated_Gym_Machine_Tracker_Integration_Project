package be.kdg.integration3.easyrep.service.routines;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.repository.routines.ExerciseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    Logger logger = LoggerFactory.getLogger(RoutineServiceImpl.class);
    private ExerciseRepository exerciseRepository;


    public MachineServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Machine createMachine(Machine machine) {
        exerciseRepository.createMachine(machine);
        return machine;
    }

    @Override
    public List<Machine> getAllMachines() {
        return exerciseRepository.getMachines();
    }

    @Override
    public void emptyRoutines() {
        exerciseRepository.emptyMachines();
    }

    @Override
    public List<Machine> findMachinesByNames(List<String> names) {
        return exerciseRepository.findByNameIn(names); // Assuming JPA repository
    }


}
