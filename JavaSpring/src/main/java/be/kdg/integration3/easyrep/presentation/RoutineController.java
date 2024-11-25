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


    @GetMapping("/add")
    public String showAddRoutine(Model model){
        logger.info("Add a new Routine");
        List<Routine> routines = routineService.getAllRoutines();
        model.addAttribute("routines", routines);
        return "routines/addRoutine";
    }

//    @PostMapping("/addRoutine")
//    public String addRoutine(@Valid @ModelAttribute ("routine") RoutineViewModel vm, BindingResult errors){
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
//            return "GymGoer/routines";
//        }
//        routineService.createRoutine(new Routine(vm.getId(), vm.getName(), vm.getMachines()));
//        return "redirect:addRoutine";
//    }


    @GetMapping("/exerciseselection")
    public String showExerciseSelection(Model model){
        logger.info("displaying exercise selection");
        List<Machine> availableGymMachines = machineService.getAllMachines();
        model.addAttribute("exercises", availableGymMachines);
        return "routines/exerciseselection";
    }
}
