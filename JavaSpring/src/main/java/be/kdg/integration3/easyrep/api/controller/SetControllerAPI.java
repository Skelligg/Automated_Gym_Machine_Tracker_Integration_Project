package be.kdg.integration3.easyrep.api.controller;


import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.service.ExerciseSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/setInput")
public class SetControllerAPI {

    Logger logger = LoggerFactory.getLogger(SetControllerAPI.class);
    private ExerciseSetService exerciseSetService;

    @Autowired
    public SetControllerAPI(ExerciseSetService exerciseSetService) {
        this.exerciseSetService = exerciseSetService;
    }

    @GetMapping
    public void inputSet(@RequestParam int setNumber, @RequestParam String setTime, @RequestParam int repCount, @RequestParam float weightCount){
        logger.debug("Inputting values");
        ExerciseSet set = new ExerciseSet(); // It is empty because the params are changed
        exerciseSetService.createExerciseSet(set);
    }

}
