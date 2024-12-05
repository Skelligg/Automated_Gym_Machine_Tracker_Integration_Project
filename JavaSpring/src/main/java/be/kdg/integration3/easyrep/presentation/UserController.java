package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.presentation.viewModels.GymGoerViewModel;
import be.kdg.integration3.easyrep.presentation.viewModels.UserCredentialsViewModel;
import be.kdg.integration3.easyrep.presentation.viewModels.UserLoginViewModel;
import be.kdg.integration3.easyrep.service.users.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("user", new UserLoginViewModel());
        return "users/login";
    }

    @PostMapping("/login")
    public String attemptLogIn(@Valid @ModelAttribute("user") UserLoginViewModel user, BindingResult br,HttpSession session, Model model) {
        return userService.attemptLogIn(user, br);
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

        // Optionally set gymgoer details into userCred
        userCred.setGymgoer(gymgoer);

        if (!userCred.getPassword().equals(userCred.getConfirmPassword())) {
            userCredErrors.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
        }

        // Check for errors in user credentials
        if (userCredErrors.hasErrors()) {
            return "users/register";
        }

        logger.debug("UserCred : {}", userCred);

        // Delegate registration logic to the service
        userService.processRegistration(gymgoer, userCred);

        // Redirect to success page or login
        return "redirect:/user/login";
    }




    @GetMapping("/{id}/user")
    public GymGoer getGymGoer(@PathVariable int id) {
        return userService.getGymGoerByUserId(id);
    }








}
