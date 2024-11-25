package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.GymStaff;
import be.kdg.integration3.easyrep.model.UserCredentials;
import be.kdg.integration3.easyrep.presentation.viewModels.GymGoerViewModel;
import be.kdg.integration3.easyrep.presentation.viewModels.UserCredentialsViewModel;
import be.kdg.integration3.easyrep.service.users.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
        model.addAttribute("gymgoer", new GymGoerViewModel());
        return "users/gymgoerdetails";
    }

    @PostMapping("/userdetails")
    public String processGymGoerDetails(
            @Valid @ModelAttribute("gymgoer") GymGoerViewModel gymgoer,
            BindingResult bindingResult, HttpSession session, Model model) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            // Return back to the gymgoer details page with validation errors
            return "users/gymgoerdetails";
        }

        // Store gymgoer object in session if no errors
        session.setAttribute("gymgoer", gymgoer);

        // Add an empty UserCredentialsViewModel for the next form
        model.addAttribute("userCred", new UserCredentialsViewModel());

        // Proceed to the registration form
        return "users/register";
    }



    @PostMapping("/register")
    public String processRegistration(
            @Valid @ModelAttribute("userCred") UserCredentialsViewModel userCred,
            BindingResult userCredErrors,
            HttpSession session, Model model) {

        // Retrieve gymgoer object from session
        GymGoerViewModel gymgoer = (GymGoerViewModel) session.getAttribute("gymgoer");

        // Check if gymgoer is null, indicating session expiry or unexpected error
        if (gymgoer == null) {
            model.addAttribute("error", "Session expired. Please fill out the form again.");
            return "users/gymgoerdetails"; // Return to gymgoer details page if session expired
        }

        // Now, you can set the gymgoer fields into userCred or process them as needed
        // Optionally, set gymgoer details into userCred (if you want to pass it forward)
        userCred.setGymgoer(gymgoer);

        if (!userCred.getPassword().equals(userCred.getConfirmPassword())) {
            userCredErrors.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
        }

        // Check for errors in user credentials
        if (userCredErrors.hasErrors()) {
            return "users/register";
        }

        logger.debug("Usercred : {}",userCred);

        userService.addGymGoer(new GymGoer(gymgoer.getFirstName(),gymgoer.getFirstName(),gymgoer.getGender(),gymgoer.getAddress()));
        userService.addUserCredentials(new UserCredentials(userCred.getUsername(),userCred.getPassword(),userCred.getEmail(), LocalDate.now()));

        // Process the registration (e.g., save to database)
        // Redirect to success page or login
        return "redirect:/user/login";
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
