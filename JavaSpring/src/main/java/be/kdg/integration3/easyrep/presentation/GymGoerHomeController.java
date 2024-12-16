package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.UserCredentials;
import be.kdg.integration3.easyrep.model.sessions.Session;
import be.kdg.integration3.easyrep.service.ExerciseSetService;
import be.kdg.integration3.easyrep.service.session.SessionService;
import be.kdg.integration3.easyrep.service.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home")
public class GymGoerHomeController {
    private Logger logger = LoggerFactory.getLogger(GymGoerHomeController.class);
    private UserService userService;
    private SessionService sessionService;
    private ExerciseSetService exerciseSetService;

    public GymGoerHomeController(UserService userService, SessionService sessionService, ExerciseSetService exerciseSetService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.exerciseSetService = exerciseSetService;
    }

    @GetMapping("/{username}")
    public String userHome(@PathVariable String username, Model model) {
        // Example: Fetch additional user-specific data to display on the homepage
        UserCredentials user = userService.getUserCredentialsByUsername(username);
        List<Session> lastSessions = sessionService.getLastSessionsByGymGoerId(3, user.getUserId());
        logger.debug("These be the last sessions: " + lastSessions);
        model.addAttribute("user", user);
        model.addAttribute("lastSessions", lastSessions);
        return "GymGoer/profile"; // The view for the user's homepage
    }

    @GetMapping("/aPreviousSession")
    public String getSessionEnd(@RequestParam("sessionId") int sessionId , @RequestParam("userId") int userId, Model model){
        logger.info("loading the fireless end page for session ID [{}] and user Id [{}]", sessionId, userId);

        Session session = sessionService.getSessionById(sessionId);
        GymGoer user = userService.getGymGoerByUserId(userId);


        //finding on which number workout the person is
        int count = sessionService.getSessionCountByUserId(userId);
        logger.info("The GymGoer is on workout number [{}]", count);

        //adding the suffix to the number
        String endNumberSuffix = getNumberEndAnnotation(count);

        //getting the duration of the session
        String duration = sessionService.getTimeForSessionById(sessionId);

        //getting name of the exercise and the weight for each set
        List<Object[]> dataFromExerciseSession = exerciseSetService.getExerciseSetsNameAndWeightBySessionId(sessionId);
        if (dataFromExerciseSession == null || dataFromExerciseSession.isEmpty()) {
            logger.warn("No exercise data found in session ID [{}]",sessionId);
        }

        //group the first column (exercise name)
        Map<Object, List<Object[]>> groupedData = dataFromExerciseSession.stream().collect(Collectors.groupingBy(entry->entry[0]));

        //adding the attributes to the model
        model.addAttribute("endNumberCount", count);
        model.addAttribute("endNumberSuffix", endNumberSuffix);
        model.addAttribute("duration", duration);
        model.addAttribute("exerciseData", groupedData);
        model.addAttribute("userName", user.getUserCredentials().getUsername());
        model.addAttribute("userId", user.getUserId());


        return "GymGoer/end_screen_session_fireless";
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
}
