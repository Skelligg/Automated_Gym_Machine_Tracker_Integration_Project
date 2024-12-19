package be.kdg.integration3.easyrep.presentation;


import be.kdg.integration3.easyrep.model.*;
import be.kdg.integration3.easyrep.model.sessions.Exercise;

import be.kdg.integration3.easyrep.service.ExerciseListService;
import be.kdg.integration3.easyrep.service.routines.RoutineExerciseService;
import be.kdg.integration3.easyrep.service.session.ExerciseService;
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
    private RoutineExerciseService routineExerciseService;
    private ExerciseListService exerciseListService;
    private UserService userService;

    public RoutineController(RoutineService routineService, RoutineExerciseService routineExerciseService, ExerciseListService exerciseListService, UserService userService) {
        this.routineService = routineService;
        this.routineExerciseService = routineExerciseService;
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
        routine.setGymGoerId(gymGoer);

        List<String> exerciseList = List.of(exerciseNames.split(","));
        for (String exerciseName : exerciseList) {
            RoutineExercise exercise = new RoutineExercise();
            exercise.setName(exerciseName);
            exercise.setRoutine(routine);
            routine.addExercise(exercise);
        }

        routineService.createRoutine(routine);
        return "redirect:/myroutines/" + username;
    }

    @GetMapping("/edit")
    public String editRoutine(@PathVariable String username,
                              @RequestParam("routineId") int routineId,
                              Model model) {
        logger.debug("HELLO");
        logger.debug("Editing routine with ID: {} for user: {}", routineId, username);

        UserCredentials user = userService.getUserCredentialsByUsername(username);
        Routine routine = routineService.getRoutine(routineId);

        // Fetch all exercises
        List<ExerciseList> allExercises = exerciseListService.getAllExercises();
        List<RoutineExercise> selectedExercises = routine.getExercises();

        // Split all exercises into left and right columns
        int midIndex = allExercises.size() / 2;
        List<ExerciseList> leftColumnExercises = allExercises.subList(0, midIndex);
        List<ExerciseList> rightColumnExercises = allExercises.subList(midIndex, allExercises.size());

        model.addAttribute("user", user);
        model.addAttribute("routine", routine);
        model.addAttribute("routineId", routineId);
        model.addAttribute("leftColumnExercises", leftColumnExercises);
        model.addAttribute("rightColumnExercises", rightColumnExercises);
        model.addAttribute("selectedExercises", selectedExercises);

        return "routines/editroutine";
    }

    @PostMapping("/edit")
    public String updateRoutine(@PathVariable String username,
                                @RequestParam("routineId") int routineId,
                                @RequestParam("exerciseNames") String exerciseNames) {
        logger.info("Updating routine ID: {} with exercises: {}", routineId, exerciseNames);

        UserCredentials user = userService.getUserCredentialsByUsername(username);
        logger.info("The user is {}", user);
        GymGoer gymGoer = userService.getGymGoerByUserId(user.getUserId());

        Routine newRoutine = new Routine();
        Routine oldRoutine = routineService.getRoutine(routineId);

        newRoutine.setRoutineName(oldRoutine.getRoutineName());
        newRoutine.setGymGoerId(gymGoer);
        oldRoutine.getExercises().clear();

        List<String> exerciseList = List.of(exerciseNames.split(","));
        for (String exerciseName : exerciseList) {
            RoutineExercise exercise = new RoutineExercise();
            exercise.setName(exerciseName);
            exercise.setRoutine(newRoutine);
            newRoutine.addExercise(exercise);
        }

        logger.info("old: {} new: {}", oldRoutine, newRoutine);

        routineService.removeRoutineById(routineId);
        routineService.createRoutine(newRoutine);
        return "redirect:/myroutines/" + username;
    }

    @PostMapping("/delete")
    public String deleteRoutine(@PathVariable String username,
                                @RequestParam("routineId") int routineId) {
        logger.info("Deleting routine with ID: {}", routineId);
        routineService.removeRoutineById(routineId);
        return "redirect:/myroutines/" + username;
    }
}