package be.kdg.integration3.easyrep.presentation;


import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.service.routines.MachineService;
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
        List<Machine> availableGymMachines = machineService.getAllMachines();
        model.addAttribute("exercises", availableGymMachines);
        model.addAttribute("routineName", routineName);
        return "routines/exerciseselection";

    }


    @PostMapping("/exerciseselection")
    public String addExercise(@RequestParam("routineName") String routineName,
                              @RequestParam List<String> exerciseNames, Model model) {

        logger.info("Creating routine '{}' with exercises {}", routineName, exerciseNames);
//
//
//        Routine routine =
//        //this line below will query the routines database asking for the nextID
//        int routineId = 1;
//        routine.setRoutineName(routineName);
//        routine.setMachines(machineService.findMachinesByNames(exerciseNames)); // Map exercises to machines

        // stop routine in database
//        routineService.createRoutine(routine);

        return "redirect:/myroutines";
    }

}
