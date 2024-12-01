package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.model.sessions.Session;
import be.kdg.integration3.easyrep.service.routines.MachineService;
import be.kdg.integration3.easyrep.service.routines.RoutineService;
import be.kdg.integration3.easyrep.service.session.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/activesession")
public class SessionController {

        Logger logger = LoggerFactory.getLogger(SessionController.class);
        SessionService sessionService;
        MachineService machineService;
        RoutineService routineService;

        public SessionController(SessionService sessionService, MachineService machineService, RoutineService routineService) {
            this.sessionService = sessionService;
            this.machineService = machineService;
            this.routineService = routineService;
        }


        @GetMapping("/usersession")
        public String getUserSession(@RequestParam("userRoutines") String routineName, Model model) {
            Routine routine = routineService.getRoutineByName(routineName);
            logger.info("Starting user session");
            List<String> machineNames = routine.getMachines().getName();
            //create exercises here with the name of the machine ---

            model.addAttribute("exercises", exercises);
            model.addAttribute("userSelectedRoutine", routine);
            model.addAttribute("session", new Session());
            return "session/session";
        }


//        //adding this so every exercise will log the sets
//        @PostMapping("/usersession")
//        public String postUserSession(@RequestParam("userRoutines") String routineName, Session session, Model model) {
//
//        }

//    @GetMapping("/usersession")
//    public String getUserSession(@RequestParam("userRoutines") String routineName,
//                                 @RequestParam(value = "index", defaultValue = "0") int currentExerciseIndex,
//                                 Model model) {
//        Routine routine = routineService.getRoutineByName(routineName);
//        logger.info("Starting user session");
//
//        // Make sure the current exercise index is passed to the view
//        model.addAttribute("userSelectedRoutine", routine);
//        model.addAttribute("currentExerciseIndex", currentExerciseIndex);
//        model.addAttribute("session", new Session());
//
//        return "session/session";
//    }
//
//    @PostMapping("/usersession")
//    public String postUserSession(@RequestParam("userRoutines") String routineName,
//                                  @RequestParam(value = "index", defaultValue = "0") int currentExerciseIndex,
//                                  Session session, Model model) {
//        // Handle saving the session data (e.g., sets, weights, reps, etc.)
//        sessionService.createSession(session);
//
//        // Proceed to the next exercise
//        currentExerciseIndex++;  // Increment the exercise index
//        Routine routine = routineService.getRoutineByName(routineName);
//        model.addAttribute("userSelectedRoutine", routine);
//        model.addAttribute("currentExerciseIndex", currentExerciseIndex);
//        model.addAttribute("session", session);
//
//        return "session/session";  // Render the same page with updated exercise
//    }






}
