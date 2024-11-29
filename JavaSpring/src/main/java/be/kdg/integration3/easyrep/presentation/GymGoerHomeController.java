package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.UserCredentials;
import be.kdg.integration3.easyrep.service.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class GymGoerHomeController {
    private Logger logger = LoggerFactory.getLogger(GymGoerHomeController.class);
    private UserService userService;

    public GymGoerHomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}/home")
    public String userHome(@PathVariable String username, Model model) {
        // Example: Fetch additional user-specific data to display on the homepage
        UserCredentials user = userService.getUserCredentialsByUsernameOrEmail(username);

        if (user == null) {
            return "error/404"; // Return a 404 error page if the user is not found
        }

        model.addAttribute("user", user);
        return "GymGoer/profile"; // The view for the user's homepage
    }



}
