package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.repository.MachineRepository;
import be.kdg.integration3.easyrep.service.routines.RoutineServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    public HashMap<Integer, Integer> findUsageOfMachineByIdPerDay(int machineId){
        HashMap<Integer, Integer> machineUsage = new HashMap<>();
        List<Object[]> result =machineRepository.findAllSetsByMachineId(machineId);

        for(Object[] row : result){
            long date = Long.parseLong((String) row[0]);
            Long usage = (Long) row[1];
            machineUsage.put((int) date, usage.intValue());
        }
        // this will have to be 12 because otherwise will be showing just one month for january
        LocalDate dateNow = LocalDate.now();
        int monthNow = dateNow.getMonthValue();
        monthNow = 12;
        for (int i = 1; i <= monthNow; i++) {
            if (!machineUsage.containsKey(i)) {
                machineUsage.put(i, 0);
            }
        }

        return machineUsage;
    }

    public HashMap<String, Integer> findUsageOfTopMachines(int gymId){
        HashMap<String, Integer> topMachineUsage = new HashMap<>();
        List<Object[]> result = machineRepository.findMostUseMachineId(gymId);

        for(Object[] row : result){
            String machineName = (String) row[0];
            Long usage = (Long) row[1];
            topMachineUsage.put(machineName, usage.intValue());
        }

        return topMachineUsage;
    }

    public List<Machine> findOurMachines() {
        logger.info("Finding our machines until id {}", ourMachines);
        return machineRepository.findByIdLessThan(ourMachines);
    }

    public List<Machine> findOverdueMachinesForGym(int gymId) {
        return machineRepository.findOverdueMachinesForGym(gymId)
                .stream()
                .filter(machine -> machine.getGym().getGymId() == gymId)
                .collect(Collectors.toList());
    }

    public boolean maintenanceRequired(int machineId) {
        Machine machine = machineRepository.findByMachineId(machineId);
        LocalDateTime lastTimeChecked = machine.getLastTimeChecked();

        if (lastTimeChecked == null) {
            return true;
        }

        // Convert LocalDateTime to LocalDate for comparison
        LocalDate lastCheckedDate = lastTimeChecked.toLocalDate();
        int maintenancePeriodInDays = machine.getMaintenancePeriodInDays();

        // Calculate the number of days since the last check
        long daysSinceLastCheck = ChronoUnit.DAYS.between(lastCheckedDate, LocalDate.now());
        return daysSinceLastCheck >= maintenancePeriodInDays;
    }



}
