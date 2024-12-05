package be.kdg.integration3.easyrep.presentation;


import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.service.MachineService;
import be.kdg.integration3.easyrep.service.routines.RoutineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/myroutines")
public class RoutineController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RoutineService routineService;
    private MachineService machineService;


    public RoutineController(RoutineService routineService, MachineService machineService) {
        this.routineService = routineService;
        this.machineService = machineService;
    }

    @GetMapping
    public String getRoutineView(Model model){
        logger.info("getRoutineView");
        List<Routine> routines = routineService.getAllRoutines();
        model.addAttribute("routines", routines);
        return "routines/routines";
    }


    //do I need to display all routines????? Not while adding
    @GetMapping("/add")
    public String showAddRoutine(Model model){
        logger.info("Accessing page to create new Routine");
        return "routines/addRoutine";
    }


    @GetMapping("/exerciseselection")
    public String showExerciseSelection(@RequestParam ("routineName") String routineName, Model model){
        logger.info("Displaying exercise selection for routine: {}", routineName);
        List<Machine> exercises = machineService.findAllMachines();
        int midIndex = exercises.size()/2;
        List<Machine> leftColumnExercises = exercises.subList(0, midIndex);
        List<Machine> rightColumnExercises = exercises.subList(midIndex, exercises.size());
        model.addAttribute("leftColumnExercises", leftColumnExercises);
        model.addAttribute("rightColumnExercises", rightColumnExercises);
        model.addAttribute("routineName", routineName);
        return "routines/exerciseselection";

    }


    @PostMapping("/exerciseselection")
    public String addExercise(@RequestParam("routineName") String routineName,
                              @RequestParam("exerciseNames") String exerciseNames) {

        logger.info("Creating routine '{}' with exercises {}", routineName, exerciseNames);

        Routine routine = new Routine();
        routine.setRoutineName(routineName);

        // Split string into list
        List<String> exerciseList = List.of(exerciseNames.split(","));
//        routine.setMachines(machineService.findMachinesByNames(exerciseList)); // Map exercises to machines

        for (String exerciseName : exerciseList) {
            Machine machine = machineService.findMachineByName(exerciseName);
            if (machine != null) {
                routine.addMachine(machine);
            } else {
                logger.warn("Machine with name '{}' not found", exerciseName);
            }
        }

        logger.info("!!The machines are {}", routine.getMachines().toString());
        logger.info(routine.toString());

        routineService.createRoutine(routine);

        return "redirect:/myroutines";
    }


    @PostMapping("/delete")
    public String deleteRoutine(@RequestParam("routineId") int routineId) {
        logger.info("Deleting routine with ID: {}", routineId);
        routineService.removeRoutine(routineId);
        return "redirect:/myroutines";
    }

}
