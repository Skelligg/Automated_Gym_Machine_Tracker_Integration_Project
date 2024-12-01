package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Machine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MachineRepository {
    private Logger logger = LoggerFactory.getLogger(MachineRepository.class);

    private static List<Machine> machines = new ArrayList<Machine>();

    public Machine createMachine(Machine machine){
        machine.setMachineId(machines.size());
        logger.info("Creating Machine with name: {}", machine.getMachineId());
        machines.add(machine);
        return machine;
    }

    public List<Machine> getMachines() {
        return machines;
    }
    
    public void emptyMachines() {
        machines.clear();
    }

    public List<Machine> findByNameIn(List<String> names) {
        List<Machine> machines = new ArrayList<>();
        for (Machine machine : machines) {
            if (names.contains(machine.getName())) {
                machines.add(machine);
            }
        }
        return machines;
    }

    public Machine readMachine(int machineId) {
        logger.debug("Reading Machine with id: {}", machineId);
        return machines.get(machineId);
    }

}
