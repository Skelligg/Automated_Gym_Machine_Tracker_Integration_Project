package be.kdg.integration3.easyrep.service.routines;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import be.kdg.integration3.easyrep.repository.routines.ExerciseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    Logger logger = LoggerFactory.getLogger(RoutineServiceImpl.class);
    private ExerciseRepository exerciseRepository;


    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Machine createMachine(Machine machine) {
        logger.info("Creating a machine {}", machine);
        exerciseRepository.createMachine(machine);
        return machine;
    }

    public void addMachine(String name, String imageAddress, MachineSet machineSetId, Arduino arduino){
        Machine machine = new Machine(name, imageAddress, machineSetId, arduino);
        logger.info("Creating a machine {}", machine);
        exerciseRepository.createMachine(machine);
    }

    //I don't understand why MachineSet is in Machine so i create the add Machine without it
    public void addMachine(String name, String imageAddress, Arduino arduino){
        Machine machine = new Machine(name, imageAddress, arduino);
        logger.info("Creating a machine {}", machine);
        exerciseRepository.createMachine(machine);
    }

    @Override
    public List<Machine> getAllMachines() {
        return exerciseRepository.getMachines();
    }

    @Override
    public void emptyMachines() {
        exerciseRepository.emptyMachines();
    }

    @Override
    public List<Machine> findMachinesByNames(List<String> names) {
        return exerciseRepository.findByNameIn(names); // Assuming JPA repository
    }

    public Machine findMachineById(int id) {
        return exerciseRepository.readMachine(id);
    }




}
