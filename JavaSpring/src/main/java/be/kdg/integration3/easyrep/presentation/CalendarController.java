package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.UserCredentials;
import be.kdg.integration3.easyrep.model.sessions.Session;
import be.kdg.integration3.easyrep.service.session.SessionService;
import be.kdg.integration3.easyrep.service.users.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home/{user}/calendar")
public class CalendarController {

    private static final Logger log = LoggerFactory.getLogger(CalendarController.class);
    private final UserService userService;
    private final SessionService sessionService;

    public CalendarController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public String getCalendar(@PathVariable("user") String username, Model model) throws JsonProcessingException {
        UserCredentials user = userService.getUserCredentialsByUsername(username);

        List<Session> allSessions = sessionService.getAllSessionsFromUser(username);
        List<String> markedDates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Loop through all sessions and extract the date
        for (Session session : allSessions) {
            String startDateTime = String.valueOf(session.getStartSession()); // Assuming this is the method to get the date

            if (startDateTime != null && !startDateTime.equals("null")) {
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(startDateTime);

                    if (dateTime != null) {
                        markedDates.add(dateTime.format(formatter));
                    }
                } catch (DateTimeParseException e) {
                    log.error("Failed to parse date: " + startDateTime, e);
                }
            } else {
                log.warn("Null or invalid date found for session: " + session.getSession_id());
            }
        }

        log.info(markedDates.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        String markedDatesJson = objectMapper.writeValueAsString(markedDates);
        model.addAttribute("markedDatesJson", markedDatesJson);

        model.addAttribute("user", user);
//        model.addAttribute("markedDates", markedDates);

        return "GymGoer/calendar";
    }


}
