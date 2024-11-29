package be.kdg.integration3.easyrep.service.routines;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import be.kdg.integration3.easyrep.repository.routines.MachineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    Logger logger = LoggerFactory.getLogger(RoutineServiceImpl.class);
    private MachineRepository machineRepository;


    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public Machine createMachine(Machine machine) {
        logger.info("Creating a machine {}", machine);
        machineRepository.createMachine(machine);
        return machine;
    }

    public void addMachine(String name, String imageAddress, MachineSet machineSetId, Arduino arduino){
        Machine machine = new Machine(name, imageAddress, machineSetId, arduino);
        logger.info("Creating a machine {}", machine);
        machineRepository.createMachine(machine);
    }

    //I don't understand why MachineSet is in Machine so i create the add Machine without it
    public void addMachine(String name, String imageAddress, Arduino arduino){
        Machine machine = new Machine(name, imageAddress, arduino);
        logger.info("Creating a machine {}", machine);
        machineRepository.createMachine(machine);
    }

    @Override
    public List<Machine> getAllMachines() {
        return machineRepository.getMachines();
    }

    @Override
    public void emptyMachines() {
        machineRepository.emptyMachines();
    }

    @Override
    public List<Machine> findMachinesByNames(List<String> names) {
        return machineRepository.findByNameIn(names); // Assuming JPA repository
    }




}
