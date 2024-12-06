package be.kdg.integration3.easyrep.presentation;


import be.kdg.integration3.easyrep.model.ExerciseList;
import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.UserCredentials;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.Routine;

import be.kdg.integration3.easyrep.presentation.viewModels.GymGoerViewModel;
import be.kdg.integration3.easyrep.service.ExerciseListService;
import be.kdg.integration3.easyrep.service.routines.ExerciseService;
import be.kdg.integration3.easyrep.service.routines.RoutineService;
import be.kdg.integration3.easyrep.service.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/myroutines/{username}")
public class RoutineController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RoutineService routineService;
    private ExerciseService exerciseService;
    private ExerciseListService exerciseListService;
    private UserService userService;

    public RoutineController(RoutineService routineService, ExerciseService exerciseService, ExerciseListService exerciseListService, UserService userService) {
        this.routineService = routineService;
        this.exerciseService = exerciseService;
        this.exerciseListService = exerciseListService;
        this.userService = userService;
    }

    @GetMapping("")
    public String getRoutineView(@PathVariable String username, Model model) {
        UserCredentials user = userService.getUserCredentialsByUsername(username);
        GymGoer gymgoer = userService.getGymGoerByUserId(user.getUserId());

        List<Routine> routines = routineService.getRoutinesByGymGoer(gymgoer);
        logger.info("!!Fetching routines {}", routines);

        model.addAttribute("routines", routines);
        model.addAttribute("user", user);
        return "routines/routines";
    }

    @GetMapping("/exerciseselection")
    public String showExerciseSelection(@PathVariable String username,
                                        @RequestParam("routineName") String routineName,
                                        Model model) {
        logger.info("Displaying exercise selection for routine: {} by user: {}", routineName, username);

        UserCredentials user = userService.getUserCredentialsByUsername(username);
        List<ExerciseList> exercises = exerciseListService.getAllExercises();
        int midIndex = exercises.size() / 2;
        List<ExerciseList> leftColumnExercises = exercises.subList(0, midIndex);
        List<ExerciseList> rightColumnExercises = exercises.subList(midIndex, exercises.size());

        model.addAttribute("user", user);
        model.addAttribute("leftColumnExercises", leftColumnExercises);
        model.addAttribute("rightColumnExercises", rightColumnExercises);
        model.addAttribute("routineName", routineName);
        return "routines/exerciseselection";
    }

    @PostMapping("/exerciseselection")
    public String addExercise(@PathVariable String username,
                              @RequestParam("routineName") String routineName,
                              @RequestParam("exerciseNames") String exerciseNames) {
        logger.info("Creating routine '{}' with exercises {} for user: {}", routineName, exerciseNames, username);

        UserCredentials user = userService.getUserCredentialsByUsername(username);
        logger.info("The user is {}", user);

        GymGoer gymGoer = userService.getGymGoerByUserId(user.getUserId());
        Routine routine = new Routine();
        routine.setRoutineName(routineName);

        List<String> exerciseList = List.of(exerciseNames.split(","));
        for (String exerciseName : exerciseList) {
            Exercise exercise = new Exercise();
            exercise.setExerciseName(exerciseName);
            exerciseService.createExercise(exercise);
            routine.addExercise(exercise);
            routine.setGymGoerId(gymGoer);
        }

        routineService.createRoutine(routine);
        return "redirect:/myroutines/" + username;
    }

    @PostMapping("/delete")
    public String deleteRoutine(@PathVariable String username,
                                @RequestParam("routineId") int routineId) {
        logger.info("Deleting routine with ID: {}", routineId);
        routineService.removeRoutine(routineId);
        return "redirect:/myroutines/" + username;
    }
}