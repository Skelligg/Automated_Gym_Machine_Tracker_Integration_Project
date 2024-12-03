package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.model.sessions.Session;
import be.kdg.integration3.easyrep.service.ExerciseSetService;
import be.kdg.integration3.easyrep.service.routines.ExerciseService;
import be.kdg.integration3.easyrep.service.routines.RoutineService;
import be.kdg.integration3.easyrep.service.session.SessionService;
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
@RequestMapping("/activesession")
public class SessionController {

    Logger logger = LoggerFactory.getLogger(SessionController.class);
    private final SessionService sessionService;
    private final ExerciseSetService exerciseSetService;
    private final RoutineService routineService;

    public SessionController(SessionService sessionService, ExerciseSetService exerciseSetService, RoutineService routineService) {
        this.sessionService = sessionService;
        this.exerciseSetService = exerciseSetService;
        this.routineService = routineService;
    }

    @GetMapping("/startSession")
    public String startSession(@RequestParam("userRoutines") String routineName, Model model) {
        // Fetch the routine by name
        Routine routine = routineService.getRoutineByName(routineName);
        logger.info("Starting session for routine: " + routineName);

        // Map machines to exercises
        List<Exercise> exercises = new ArrayList<>();
        for (Machine machine : routine.getMachines()) {
            logger.debug(machine.toString());
            //IDK WHAT THIS MEANS SO I COMMENT IT OUT
//            exercises.add(new Exercise(machine.getName()));
        }

        // Create and save the session
        Session session = new Session();
        session.setExercises(exercises);
        session.setId(1);
        sessionService.createSession(session);

        // Redirect to the first exercise
        int sessionId = session.getId();
        return "redirect:/activesession/nextExercise?sessionId=" + sessionId + "&exerciseIndex=-1";
    }

    @GetMapping("/nextExercise")
    public String getNextExercise(@RequestParam("sessionId") int sessionId,
                                  @RequestParam("exerciseIndex") int exerciseIndex,
                                  Model model) {
        // Fetch the session by ID
        Session session = sessionService.getSessionById(sessionId);
        exerciseIndex++;

        // Get the current exercise
        Exercise currentExercise = session.getExercises().get(exerciseIndex);

        // Add attributes to the model
        model.addAttribute("exercise", currentExercise);
        model.addAttribute("sessionId", sessionId);
        model.addAttribute("sessionSize", session.getExercises().size());
        model.addAttribute("exerciseIndex", exerciseIndex);

        return "GymGoer/currentExercise";
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






