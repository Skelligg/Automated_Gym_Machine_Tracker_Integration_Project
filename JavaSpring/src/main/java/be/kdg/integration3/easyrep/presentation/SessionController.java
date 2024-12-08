package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.model.RoutineExercise;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.model.sessions.Session;
import be.kdg.integration3.easyrep.service.ExerciseSetService;
import be.kdg.integration3.easyrep.service.routines.RoutineService;
import be.kdg.integration3.easyrep.service.session.ExerciseService;
import be.kdg.integration3.easyrep.service.session.SessionService;
import be.kdg.integration3.easyrep.service.users.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/activesession/{username}")
public class SessionController {

    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(SessionController.class);
    private final SessionService sessionService;
    private final ExerciseSetService exerciseSetService;
    private final RoutineService routineService;
    private final ExerciseService exerciseService;


    public SessionController(SessionService sessionService, ExerciseSetService exerciseSetService, RoutineService routineService, UserService userService, ExerciseService exerciseService) {
        this.sessionService = sessionService;
        this.exerciseSetService = exerciseSetService;
        this.routineService = routineService;
        this.userService = userService;
        this.exerciseService = exerciseService;
    }

    @GetMapping("/startSession")
    public String startSession(@PathVariable String username,
                               @RequestParam("userRoutine") int routineId,
                               Model model) {
        // Fetch the routine by name
        Routine routine = routineService.getRoutine(routineId);
        logger.info("Starting session for routine: " + routineId);


        GymGoer user = userService.getGymGoerByUserId(userService.getUserCredentialsByUsername(username).getUserId()) ;

        // Create and save the session
        Session session = new Session();
        session.setGymGoerId(user);

        // Map machines to exercises
        List<Exercise> exercises = new ArrayList<>();
        for (RoutineExercise rExercise : routine.getExercises()) {
            Exercise exercise = new Exercise();
            exercise.setExerciseName(rExercise.getName());
            exercise.setSession(session);
            exercises.add(exercise);
            //IDK WHAT THIS MEANS SO I COMMENT IT OUT
//            exercises.add(new Exercise(machine.getName()));
        }

        session.setExercises(exercises);
        sessionService.createSession(session);

        // Redirect to the first exercise
        int sessionId = session.getSession_id();
        return "redirect:/activesession/" + username + "/nextExercise?sessionId=" + sessionId + "&exerciseIndex=0";
    }

    @GetMapping("/nextExercise")
    public String getNextExercise(@PathVariable String username,
                                  @RequestParam("sessionId") int sessionId,
                                  @RequestParam("exerciseIndex") int exerciseIndex,
                                  Model model,
                                  HttpSession httpSession) {
        // Fetch the session by ID
        Session session = sessionService.getSessionById(sessionId);
        // Get the current exercise
        Exercise currentExercise = session.getExercises().get(exerciseIndex);

        httpSession.setAttribute("currentExerciseId", currentExercise.getExerciseId());

        List<ExerciseSet> sets = exerciseSetService.findExerciseSetsByExercise(currentExercise); // Fetch sets for this exercise

        // Add attributes to the model
        model.addAttribute("exercise", currentExercise);
        model.addAttribute("sets", sets);
        model.addAttribute("sessionId", sessionId);
        model.addAttribute("sessionSize", session.getExercises().size());
        model.addAttribute("exerciseIndex", exerciseIndex);

        return "GymGoer/currentExercise";
    }

    @GetMapping("/setInput")
    public void inputSet(@RequestParam int machineId,
                         @RequestParam int setNumber,
                         @RequestParam String setTime,
                         @RequestParam int repCount,
                         @RequestParam float weightCount,
                         HttpSession httpSession) {
        logger.debug("Processing set input for machineId: {}", machineId);

        //Session activeSession = sessionService.getActiveSessionByMachineId(machineId);

        Integer exerciseId = (Integer) httpSession.getAttribute("currentExerciseId");
        if (exerciseId == null) {
            throw new IllegalStateException("No active exercise found for this session.");
        }

        ExerciseSet set = new ExerciseSet();
        set.setSetNumber(setNumber);
        set.setEndTime(exerciseSetService.stringToLocalTime(setTime));
        //set.setRepetitionNumber(repCount);
        set.setWeightCount(weightCount);
        Exercise exercise = exerciseService.getExerciseById(exerciseId); // Fetch the associated exercise
        set.setExercise(exercise);

        exerciseSetService.createExerciseSet(set);
    }

    @GetMapping("/end")
    public String getSessionEnd(@RequestParam("sessionId") int sessionId,Model model) {
        logger.info("Mapping the end screen");
        List<ExerciseSet> statistics = exerciseSetService.findAllExerciseSet();
        logger.info("Found {} statistics", statistics);
        model.addAttribute("statistics", statistics);
        return "GymGoer/end_screen_session";
    }

    @PostMapping("/GymGoer/statistics/open")
    public String getStatistics() {
        logger.info("Mapping the statistics screen");
        return "redirect:GymGoer/statistics";
    }
}






