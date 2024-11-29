package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.model.sessions.MachineSet;
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

import java.util.List;

@Controller
@RequestMapping("/activesession")
public class SessionController {

        Logger logger = LoggerFactory.getLogger(SessionController.class);
        SessionService sessionService;
        MachineService machineService;
        RoutineService routineService;

        public SessionController(SessionService sessionService, MachineService machineService) {
            this.sessionService = sessionService;
            this.machineService = machineService;
        }


        @GetMapping
        public String getSession(Model model) {
            logger.info("Starting session");

            //need name of selected routine

            model.addAttribute("session", new Session());
            return "session/session";
        }

}
