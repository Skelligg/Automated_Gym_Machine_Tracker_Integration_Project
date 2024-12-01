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
    private final MachineRepository machineRepository;

    public DataInitializer(GymRepository gymRepository, MachineRepository machineRepository, RoutineRepository routineRepository) {
        this.gymRepository = gymRepository;
        this.machineRepository = machineRepository;
        initializeData();
    }

    private void initializeData() {
        // Create machines
        Machine machine1 = new Machine("row machine", "/images/machinesPics/MachineRow.gif");
        Machine machine2 = new Machine("bench press", "/images/machinesPics/MachineRow.gif");
        Machine machine3 = new Machine("squat machine", "/images/machinesPics/MachineRow.gif");
        Machine machine4 = new Machine("unilateral jerk", "/images/machinesPics/MachineRow.gif");

        // Add machines to the repository
        machineRepository.createMachine(machine1);
        machineRepository.createMachine(machine2);
        machineRepository.createMachine(machine3);
        machineRepository.createMachine(machine4);

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

