package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.GymStaff;
import be.kdg.integration3.easyrep.model.UserCredentials;
import be.kdg.integration3.easyrep.service.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogIn(Model model) {
        return "users/login";
    }

    @GetMapping("/userdetails")
    public String getUserDetails(Model model) {
        return "users/userdetails";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        return "users/register";
    }

    @GetMapping("/{id}/credentials")
    public UserCredentials getUserCredentials(@PathVariable int id) {
        return userService.getUserCredentialsById(id);
    }

    @GetMapping("/{id}/staff")
    public GymStaff getGymStaff(@PathVariable int id) {
        return userService.getGymStaffByUserId(id);
    }

    @GetMapping("/{id}/goer")
    public GymGoer getGymGoer(@PathVariable int id) {
        return userService.getGymGoerByUserId(id);
    }


    @PostMapping("/add-credentials")
    public void addUserCredentials(@RequestBody UserCredentials userCredentials) {
        userService.addUserCredentials(userCredentials);
    }

    @PostMapping("/add-staff")
    public void addGymStaff(@RequestBody GymStaff gymStaff) {
        userService.addGymStaff(gymStaff);
    }

    @PostMapping("/add-goer")
    public void addGymGoer(@RequestBody GymGoer gymGoer) {
        userService.addGymGoer(gymGoer);
    }


}
