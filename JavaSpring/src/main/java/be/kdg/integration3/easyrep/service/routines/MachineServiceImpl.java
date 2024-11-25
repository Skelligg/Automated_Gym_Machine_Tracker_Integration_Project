package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.repository.MachineRepository;
import be.kdg.integration3.easyrep.repository.RoutineRepository;
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
        machineRepository.createMachine(machine);
        return machine;
    }

    @Override
    public List<Machine> getAllMachines() {
        return machineRepository.getMachines();
    }

    @Override
    public void emptyRoutines() {
        machineRepository.emptyMachines();
    }

}
