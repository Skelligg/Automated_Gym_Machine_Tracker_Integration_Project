package be.kdg.integration3.easyrep.presentation;


import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.service.ExerciseSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/")
public class SessionEndController {
    private final Logger logger = LoggerFactory.getLogger(SessionEndController.class);

    private final ExerciseSetService exerciseSetService;

    @Autowired
    public SessionEndController(ExerciseSetService exerciseSetService) {
        this.exerciseSetService = exerciseSetService;
    }

    @GetMapping("/session/end")
    public String getSessionEnd(Model model) {
        logger.info("Mapping the end screen");
        List<ExerciseSet> statistics = exerciseSetService.getAllExerciseSets();
        logger.info("Found {} statistics", statistics);
        model.addAttribute("statistics", statistics);
        return "GymGoer/end_screen_session";
    }

    @PostMapping("/GymGoer/statistics/open")
    public String getStatistics(){
        logger.info("Mapping the statistics screen");
        return "redirect:GymGoer/statistics";
    }

}
