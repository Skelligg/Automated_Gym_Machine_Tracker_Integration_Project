package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.sessions.PlayerStatisticsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class SessionEndController {
    private final Logger logger = LoggerFactory.getLogger(SessionEndController.class);

    @GetMapping("/session/end")
    public String getSessionEnd(Model model) {
        logger.info("Mapping the end screen");
        return "GymGoer/end_screen_session";
    }
//    @PostMapping
//    public String getToHomePage(Model model) {
//
//    }
}
