package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.UserCredentials;
import be.kdg.integration3.easyrep.service.users.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/{user}/calendar")
public class CalendarController {

    private final UserService userService;

    public CalendarController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getCalendar(@PathVariable("user") String username, Model model) {
        UserCredentials user = userService.getUserCredentialsByUsername(username);
        model.addAttribute("user", user);
        return "GymGoer/calendar";
    }

}
