package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Gym;
import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.repository.GymRepository;
import be.kdg.integration3.easyrep.repository.routines.MachineRepository;
import be.kdg.integration3.easyrep.repository.routines.RoutineRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class DataInitializer {
    private final GymRepository gymRepository;
    private final MachineRepository machineRepository;

    public DataInitializer(GymRepository gymRepository, MachineRepository machineRepository, RoutineRepository routineRepository) {
        this.gymRepository = gymRepository;
        this.machineRepository = machineRepository;
        initializeData();
    }

    private void initializeData() {
        // Create machines
        Machine machine11 = new Machine(11, "row machine", "/static/images/machinesPics/MachineRow.mp4");
        Machine machine12 = new Machine(12, "bench press", "/static/images/machinesPics/MachineBenchPress.mp4");
        Machine machine13 = new Machine(13, "squat machine", "/static/images/machinesPics/MachineSquat.mp4");
        Machine machine14 = new Machine(14, "unilateral jerk", "/static/images/machinesPics/MachineJerk.mp4");

        // Add machines to the repository
        machineRepository.createMachine(machine11);
        machineRepository.createMachine(machine12);
        machineRepository.createMachine(machine13);
        machineRepository.createMachine(machine14);

        // Create gyms
        Gym gym1 = new Gym("Fasic Bit", 4, "Streetname 34, 2000 Antwerp", new Date(2021, 7, 14));
        Gym gym2 = new Gym("Fasic Bit", 5, "Streetname 71, 2050 Antwerp", new Date(2022, 11, 20));

        // Assign machines to gyms
        gym1.addMachine(machine11);
        gym1.addMachine(machine12);
        gym1.addMachine(machine13);
        gym1.addMachine(machine14);

        // Add gyms to the repository
        gymRepository.save(gym1);
        gymRepository.save(gym2);
    }
}

