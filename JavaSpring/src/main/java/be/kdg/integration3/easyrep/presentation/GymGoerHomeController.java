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

    @GetMapping("/{username}/sessions")
    public String getPastSession(@PathVariable String username,@RequestParam("sessionId") int sessionId,Model model){
        logger.info("loading the end page for session ID [{}] and user [{}]", sessionId, username);

        UserCredentials userCredentials = userService.getUserCredentialsByUsername(username);
        GymGoer user = userService.getGymGoerByUserId(userCredentials.getUserId());
        Session session = sessionService.getSessionById(sessionId);

        //finding on which number workout the person is
        int sessionNumber = sessionService.getSessionSequenceForUser(user.getUserId(),sessionId);


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
//        model.addAttribute("endNumberCount", count);
        model.addAttribute("duration", duration);
        model.addAttribute("exerciseData", groupedData);
        model.addAttribute("userName", user.getUserCredentials().getUsername());
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("sessionId", sessionId);
        model.addAttribute("sessionNumber", sessionNumber);



        return "GymGoer/past_session";
    }

    @GetMapping("/{username}/workouts")
    public String userWorkouts(@PathVariable String username, Model model) {
        // fetch user and sessions
        UserCredentials user = userService.getUserCredentialsByUsername(username);
        List<Session> lastSessions = sessionService.getLastSessionsByGymGoerId(250, user.getUserId());

        logger.debug("Workouts page sessions: " + lastSessions);

        // Add attributes to the model
        model.addAttribute("user", user);
        model.addAttribute("lastSessions", lastSessions);
        return "GymGoer/workouts";
    }
}
