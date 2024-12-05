package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.repository.MachineRepository;
import be.kdg.integration3.easyrep.service.routines.RoutineServiceImpl;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService{

    Logger logger = LoggerFactory.getLogger(RoutineServiceImpl.class);

    private MachineRepository machineRepository;


    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Transactional
    public Machine createMachine(Machine machine) {
        logger.info("Creating a machine {}", machine);
       return machineRepository.save(machine);
    }

    public Machine findMachineById(int id) {
        logger.info("Finding machine with id {}", id);
        return machineRepository.findById(id);
    }

    public Machine findMachinesByNames(String name) {
        logger.info("Finding machines by names {}", name);
        return machineRepository.findByName(name);
    }

    public Machine findMachineByName(String name) {
        return machineRepository.findByName(name);
    }

    public List<Machine> findAllMachines() {
        logger.info("Getting all machines");
        return machineRepository.findAll();
    }

    @Transactional
    public void deleteMachine(int id){
        logger.info("Deleting machine {}", id);
        Machine machine = findMachineById(id);
        machineRepository.delete(machine);
    }
    @Transactional
    public void updateMachine(Machine machine) {
        logger.info("Updating machine {}", machine);
        machineRepository.save(machine);
    }



    public List<Machine> findOurMachines() {
        logger.info("Finding our machines until id {}", 20);
        return machineRepository.findByIdLessThan(20);
    }





}
