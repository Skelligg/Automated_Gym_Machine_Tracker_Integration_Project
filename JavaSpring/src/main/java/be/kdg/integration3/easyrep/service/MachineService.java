package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.repository.MachineRepository;
import be.kdg.integration3.easyrep.repository.routines.ExerciseRepository;
import be.kdg.integration3.easyrep.service.routines.RoutineServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService{

    Logger logger = LoggerFactory.getLogger(RoutineServiceImpl.class);
    private MachineRepository machineRepository;


    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public Machine createMachine(Machine machine) {
        logger.info("Creating a machine {}", machine);
        machineRepository.createMachine(machine);
        return machine;
    }

    public void addMachine(String name, String imageAddress, Arduino arduino){
        Machine machine = new Machine(name, imageAddress, arduino);
        logger.info("Creating a machine {}", machine);
        machineRepository.createMachine(machine);
    }

    public List<Machine> getAllMachines() {
        return machineRepository.getMachines();
    }


    public void emptyMachines() {
        machineRepository.emptyMachines();
    }


    public List<Machine> findMachinesByNames(List<String> names) {
        return machineRepository.findByNameIn(names); // Assuming JPA repository
    }

    public Machine findMachineById(int id) {
        return machineRepository.readMachine(id);
    }




}
