package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.GymStaff;
import be.kdg.integration3.easyrep.model.UserCredentials;
import be.kdg.integration3.easyrep.presentation.viewModels.GymGoerViewModel;
import be.kdg.integration3.easyrep.presentation.viewModels.UserCredentialsViewModel;
import be.kdg.integration3.easyrep.presentation.viewModels.UserLogin;
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
        model.addAttribute("user", new UserLogin());
        return "users/login";
    }

    @PostMapping("/login")
    public String attemptLogIn(@Valid @ModelAttribute("user") UserLogin user, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "users/login";
        }

        // Call the service for login validation and get the redirection path
        String redirectPath = userService.attemptLogIn(user, br);

        if (br.hasErrors()) {
            return "users/login";
        }

        return redirectPath; // Redirect if successful
    }


//    @PostMapping("/login")
//    public String attemptLogIn(@Valid @ModelAttribute("user") UserLogin user, BindingResult br, Model model) {
//        if (br.hasErrors()) {
//            return "users/login";
//        }
//
//        // Search by username or email
//        UserCredentials userCheck = userService.getUserCredentialsByUsernameOrEmail(user.getUsernameOrEmail());
//
//        if (userCheck == null) {
//            br.rejectValue("usernameOrEmail", "username.not.found", "Username or email not found");
//            return "users/login";
//        }
//
//        if (!userCheck.getPassword().equals(user.getPassword())) { // Check the password
//            br.rejectValue("password", "password.incorrect", "Incorrect password");
//            return "users/login";
//        }
//
//        // Redirect to user's home page if successful
//        if(userCheck.getUsername().contains("gymstaff")){
//            return "redirect:/GymOwner";
//
//        }
//        else{
//            return "redirect:/"+ userCheck.getUsername() +"/home";
//
//        }
//    }


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

        logger.debug("Usercred : {}", userCred);

        userService.addGymGoer(new GymGoer(gymgoer.getFirstName(), gymgoer.getFirstName(), gymgoer.getGender(), gymgoer.getAddress()));
        userService.addUserCredentials(new UserCredentials(userCred.getUsername(), userCred.getPassword(), userCred.getEmail(), LocalDate.now()));

        // Process the registration (e.g., save to database)
        // Redirect to success page or login
        return "redirect:/user/login";
    }


    @GetMapping("/{id}/user")
    public GymGoer getGymGoer(@PathVariable int id) {
        return userService.getGymGoerByUserId(id);
    }
}






