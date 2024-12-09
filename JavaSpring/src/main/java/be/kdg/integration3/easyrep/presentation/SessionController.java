package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.*;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.model.sessions.Session;
import be.kdg.integration3.easyrep.service.ExerciseSetService;
import be.kdg.integration3.easyrep.service.GymService;
import be.kdg.integration3.easyrep.service.MachineService;
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
@RequestMapping("/activesession")
public class SessionController {

    private final UserService userService;
    private final GymService gymService;
    private final MachineService machineService;
    Logger logger = LoggerFactory.getLogger(SessionController.class);
    private final SessionService sessionService;
    private final ExerciseSetService exerciseSetService;
    private final RoutineService routineService;
    private final ExerciseService exerciseService;


    public SessionController(SessionService sessionService, ExerciseSetService exerciseSetService, RoutineService routineService, UserService userService, ExerciseService exerciseService, GymService gymService, MachineService machineService) {
        this.sessionService = sessionService;
        this.exerciseSetService = exerciseSetService;
        this.routineService = routineService;
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.gymService = gymService;
        this.machineService = machineService;
    }

    @GetMapping("/{username}/startSession")
    public String startSession(@PathVariable String username,
                               @RequestParam("userRoutine") int routineId,
                               @RequestParam("gymId") int gymId,
                               Model model) {
        // Fetch the routine by name
        Routine routine = routineService.getRoutine(routineId);
        logger.info("Starting session for routine: " + routineId);

        Gym currentGym = gymService.findGymById(gymId);

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
            Machine machine = machineService.findMachineByName(rExercise.getName());
            exercise.setMachine(machine);
            exercises.add(exercise);

        }

        session.setExercises(exercises);
        session.setStatus("active");
        sessionService.createSession(session);

        int machineId = exercises.get(0).getMachine().getMachineId();

        // Redirect to the first exercise
        int sessionId = session.getSession_id();
        return "redirect:/activesession/" + username + "/nextExercise?sessionId=" + sessionId + "&exerciseIndex=0" + "&machineId=" + machineId;
    }

    @GetMapping("/{username}/nextExercise")
    public String getNextExercise(@PathVariable String username,
                                  @RequestParam("sessionId") int sessionId,
                                  @RequestParam("exerciseIndex") int exerciseIndex,
                                  @RequestParam("machineId") int machineId,
                                  Model model,
                                  HttpSession httpSession) {
        // Fetch the session by ID
        Session session = sessionService.getSessionById(sessionId);
        // Get the current exercise
        Exercise currentExercise = session.getExercises().get(exerciseIndex);

        httpSession.setAttribute("currentExerciseId", currentExercise.getExerciseId());

        List<ExerciseSet> sets = exerciseSetService.findExerciseSetsByExercise(currentExercise); // Fetch sets for this exercise

        logger.info("Session found by machineID is {} ",sessionService.getActiveSessionByMachineId(machineId));

        // Add attributes to the model
        model.addAttribute("exercise", currentExercise);
        model.addAttribute("sets", sets);
        model.addAttribute("sessionId", sessionId);
        model.addAttribute("sessionSize", session.getExercises().size());
        model.addAttribute("exerciseIndex", exerciseIndex);

        return "GymGoer/currentExercise";
    }

    @GetMapping("/setInput")
    @ResponseBody
    public void inputSet(@RequestParam int machineId,
                         @RequestParam int setNumber,
                         @RequestParam String setTime,
                         @RequestParam int repCount,
                         @RequestParam float weightCount) {
        logger.debug("Processing set input for machineId: {}", machineId);

        Session activeSession = sessionService.getActiveSessionByMachineId(machineId);

        if (!activeSession.getStatus().equals("active")) {
            throw new IllegalStateException("No active session found for machine ID: " + machineId);
        }

        Exercise currentExercise = activeSession.getExercises().stream()
                .filter(exercise -> exercise.getMachine().getMachineId() == machineId)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No active exercise found for machine ID: " + machineId));; // Adjust logic to get the current exercise
        if (currentExercise == null) {
            throw new IllegalStateException("No active exercise found for machine ID: " + machineId);
        }

        ExerciseSet set = new ExerciseSet();
        set.setSetNumber(setNumber);
        set.setEndTime(exerciseSetService.stringToLocalTime(setTime));
        set.setRepetitionCount(repCount);
        set.setWeightCount(weightCount);
        set.setExercise(currentExercise);

        exerciseSetService.createExerciseSet(set);
    }

    @GetMapping("/end")
    public String getSessionEnd(@RequestParam("sessionId") int sessionId,Model model) {
        logger.info("Mapping the end screen");
        Session session = sessionService.getSessionById(sessionId);
        session.setStatus("ended");

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






