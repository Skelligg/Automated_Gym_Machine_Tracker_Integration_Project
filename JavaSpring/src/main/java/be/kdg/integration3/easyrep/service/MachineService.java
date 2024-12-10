package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.repository.MachineRepository;
import be.kdg.integration3.easyrep.service.routines.RoutineServiceImpl;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MachineService{

    Logger logger = LoggerFactory.getLogger(RoutineServiceImpl.class);

    private MachineRepository machineRepository;
    private final int ourMachines = 20; //amount of machines that we offer, so the ones that are in gym 0, this can change if we add more machines

    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public void createMachine(Machine machine) {
        logger.info("Creating a machine {}", machine);
        machineRepository.save(machine);
    }

    public Machine findMachineById(int id) {
        logger.info("Finding machine with id {}", id);
        return machineRepository.findByMachineId(id);
    }

    public Machine findMachineByName(String name) {
        return machineRepository.findMachineByName(name);
    }

    public List<Machine> findAllMachines() {
        logger.info("Getting all machines");
        return machineRepository.findAll();
    }

    public void deleteMachine(int id){
        logger.info("Deleting machine {}", id);
        Machine machine = findMachineById(id);
        machineRepository.delete(machine);
    }
    public void updateMachine(Machine machine) {
        logger.info("Updating machine {}", machine);
        machineRepository.save(machine);
    }

    public HashMap<String, Integer> findUsageOfMachineByIdPerDay(int machineId){
        HashMap<String, Integer> machineUsage = new HashMap<>();
        List<Object[]> result =machineRepository.findAllSetsByMachineId(machineId);

        for(Object[] row : result){
            String date = (String) row[0];
            Long usage = (Long) row[1];
            machineUsage.put(date, usage.intValue());
        }

        return machineUsage;
    }

    public List<Machine> findOurMachines() {
        logger.info("Finding our machines until id {}", ourMachines);
        return machineRepository.findByIdLessThan(ourMachines);
    }





}
