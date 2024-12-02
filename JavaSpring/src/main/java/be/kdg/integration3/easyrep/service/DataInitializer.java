package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Gym;
import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.repository.GymRepository;
import be.kdg.integration3.easyrep.repository.MachineRepository;
import be.kdg.integration3.easyrep.repository.routines.ExerciseRepository;
import be.kdg.integration3.easyrep.repository.routines.RoutineRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataInitializer {
    private final GymRepository gymRepository;
    private final MachineService machineService;
    private final MachineService ourmachineService;

    public DataInitializer(GymRepository gymRepository, MachineService machineService, MachineService ourmachineService) {
        this.gymRepository = gymRepository;
        this.machineService = machineService;
        this.ourmachineService = ourmachineService;
        initializeData();
    }

    private void initializeData() {

        // Create our machines
        Machine machine10 = new Machine("Our row machine", "/images/machinesPics/MachineRow.gif");
        Machine machine20 = new Machine("Our bench press", "/images/machinesPics/MachineRow.gif");
        Machine machine30 = new Machine("Our squat machine", "/images/machinesPics/MachineRow.gif");
        Machine machine40 = new Machine("Our unilateral jerk", "/images/machinesPics/MachineRow.gif");

        ourmachineService.createOurMachines(machine10);
        ourmachineService.createOurMachines(machine20);
        ourmachineService.createOurMachines(machine30);
        ourmachineService.createOurMachines(machine40);


        // Create machines
        Machine machine1 = new Machine("Row machine", "/images/machinesPics/MachineRow.gif");
        Machine machine2 = new Machine("Bench press", "/images/machinesPics/MachineRow.gif");
        Machine machine3 = new Machine("Squat machine", "/images/machinesPics/MachineRow.gif");
        Machine machine4 = new Machine("Unilateral jerk", "/images/machinesPics/MachineRow.gif");

        // Add machines to the repository
        machineService.createMachine(machine1);
        machineService.createMachine(machine2);
        machineService.createMachine(machine3);
        machineService.createMachine(machine4);

        // Create gyms
        Gym gym1 = new Gym("Fasic Bit", 4, "Streetname 34, 2000 Antwerp", new Date(2021, 7, 14));
        Gym gym2 = new Gym("Fasic Bit", 5, "Streetname 71, 2050 Antwerp", new Date(2022, 11, 20));

        // Assign machines to gyms
        gym1.addMachine(machine1);
        gym1.addMachine(machine2);
        gym1.addMachine(machine3);
        gym1.addMachine(machine4);

        // Add gyms to the repository
        gymRepository.save(gym1);
        gymRepository.save(gym2);
    }
}

