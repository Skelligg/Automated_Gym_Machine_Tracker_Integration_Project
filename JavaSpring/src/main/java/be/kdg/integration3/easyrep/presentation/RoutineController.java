package be.kdg.integration3.easyrep.presentation;


import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.service.RoutineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
    public String addRoutine(Model model){
        logger.info("addRoutine");
        model.addAttribute("routine", new Routine());
        return "GymGoer/addRoutine";
    }

    @PostMapping("/add")
    public String processAddRoutine(@RequestParam int id, @RequestParam String name, @RequestParam List<Machine> machines){

    }
}
