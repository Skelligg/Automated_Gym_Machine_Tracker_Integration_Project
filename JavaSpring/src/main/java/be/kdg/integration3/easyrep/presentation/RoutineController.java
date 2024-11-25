package be.kdg.integration3.easyrep.presentation;


import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.presentation.viewModels.RoutineViewModel;
import be.kdg.integration3.easyrep.service.RoutineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/myRoutines")
public class RoutineController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RoutineService routineService;


    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @GetMapping
    public String getRoutineView(Model model){
        logger.info("getRoutineView");
        List<Routine> routines = routineService.getAllRoutines();
        model.addAttribute("routines", routines);
        return "GymGoer/routines";
    }


    @GetMapping("/add")
    public String showAddRoutine(Model model){
        logger.info("Add a new Routine");
        model.addAttribute("routine", new RoutineViewModel());
        return "GymGoer/addRoutine";
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
}
