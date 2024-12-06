package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.model.sessions.Session;
import be.kdg.integration3.easyrep.service.ExerciseSetService;
import be.kdg.integration3.easyrep.service.routines.RoutineService;
import be.kdg.integration3.easyrep.service.session.SessionService;
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
    public String startSession(@PathVariable String username,
                               @RequestParam("userRoutines") String routineName,
                               Model model) {
        // Fetch the routine by name
        Routine routine = routineService.getRoutineByName(routineName);
        logger.info("Starting session for routine: " + routineName);

        // Map machines to exercises
        List<Exercise> exercises = new ArrayList<>();
        for (Exercise exercise : routine.getExercises()) {
            logger.debug(exercise.toString());
            //IDK WHAT THIS MEANS SO I COMMENT IT OUT
//            exercises.add(new Exercise(machine.getName()));
        }

        // Create and save the session
        Session session = new Session();
        session.setExercises(exercises);
        session.setSession_id(1);
        sessionService.createSession(session);

        // Redirect to the first exercise
        int sessionId = session.getSession_id();
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
    public String getSessionEnd(@RequestParam("sessionId") int sessionId ,@RequestParam("userId") int userId,Model model){
        logger.info("Loading the end page.");

        //finding on which number workout the person is
        int count = sessionService.getSessionCountByUserId(userId);
        logger.info("The GymGoer is on workout number [{}]", count);

        //adding the suffix to the number
        String endNumberSuffix = getNumberEndAnnotation(count);

        //getting the duration of the session
        String duration = sessionService.getTimeForSessionById(sessionId);

        //getting name of the exercise and the weight for each set
        List<Object[]> dataFromExerciseSession = exerciseSetService.getExerciseSetsNameAndWeightBySessionId(sessionId);

        //adding the attributes to the model
        model.addAttribute("endNumberCount", count);
        model.addAttribute("endNumberSuffix", endNumberSuffix);
        model.addAttribute("duration", duration);
        model.addAttribute("exerciseData", dataFromExerciseSession);

        return "GymGoer/end_screen_session";
    }
    //going among the numbers and seeing if they are supposed to end on 'st', 'nd' etc.
    private String getNumberEndAnnotation(int number) {
        if (number % 100 >= 11 && number % 100 <= 13) return "th"; // for evey number that end on 'th'
        return switch (number %10){
            case 1 -> "st"; //for the first
            case 2 -> "nd"; // for the second
            case 3 -> "rd"; // for the third
            default -> "th";
        };
    }


    @PostMapping("/GymGoer/statistics/open")
    public String getStatistics() {
        logger.info("Mapping the statistics screen");
        return "redirect:GymGoer/statistics";
    }

}






