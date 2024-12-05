package be.kdg.integration3.easyrep.service.users;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.GymStaff;
import be.kdg.integration3.easyrep.model.UserCredentials;
import be.kdg.integration3.easyrep.presentation.viewModels.GymGoerViewModel;
import be.kdg.integration3.easyrep.presentation.viewModels.UserCredentialsViewModel;
import be.kdg.integration3.easyrep.presentation.viewModels.UserLoginViewModel;
import be.kdg.integration3.easyrep.repository.users.GymGoerRepository;
import be.kdg.integration3.easyrep.repository.users.GymStaffRepository;
import be.kdg.integration3.easyrep.repository.users.UserCredentialsRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;

@Service
public class UserServiceImp implements UserService {
    private UserCredentialsRepository userCredentialsRepository;
    private GymStaffRepository gymStaffRepository;
    private GymGoerRepository gymGoerRepository;

    public UserServiceImp(UserCredentialsRepository userCredentialsRepository, GymStaffRepository gymStaffRepository, GymGoerRepository gymGoerRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.gymStaffRepository = gymStaffRepository;
        this.gymGoerRepository = gymGoerRepository;
    }

    // Add a new user
    @Override
    public void addUserCredentials(UserCredentials userCredentials) {
        userCredentialsRepository.save(userCredentials);
    }

    // Add gym staff
    @Override
    public void addGymStaff(GymStaff gymStaff) {
        gymStaffRepository.save(gymStaff);
    }

    // Add gym goer
    @Override
    public void addGymGoer(GymGoer gymGoer) {
        gymGoerRepository.save(gymGoer);
    }

    // Find user credentials by ID
    @Override
    public UserCredentials getUserCredentialsByUsername(String username) {
        return userCredentialsRepository.findByUsernameOrEmail(username);
    }

    // Find gym staff by user ID
    @Override
    public GymStaff getGymStaffByUserId(int userId) {
        return gymStaffRepository.findByUserId(userId);
    }

    // Find gym goer by user ID
    @Override
    public GymGoer getGymGoerByUserId(int userId) {
        return gymGoerRepository.findByuserId(userId);
    }

    @Override
    public String attemptLogIn(UserLoginViewModel user, BindingResult br) {
        if (br.hasErrors()) {
            return "users/login";
        }

        try {

            UserCredentials userCheck = userCredentialsRepository.findByUsernameOrEmail(user.getUsernameOrEmail());

            if (userCheck == null) {
                br.rejectValue("usernameOrEmail", "username.not.found", "Username or email not found");
                return "users/login";
            }

            // Validate the password
            if (!userCheck.getPassword().equals(user.getPassword())) {
                br.rejectValue("password", "password.incorrect", "Incorrect password");
                return "users/login";
            }

            // Check if the user is a gym staff member
            GymStaff gymStaff = gymStaffRepository.findByUserId(userCheck.getUserId());

            // Determine the redirection path
            if (gymStaff != null) {
                return "redirect:/" + gymStaff.getGymId() + "/GymOwner";
            } else {
                return "redirect:/home/"  + userCheck.getUsername();
            }
        } catch (NullPointerException e) {
            // Handle potential null pointer exceptions
            br.reject("error.null.pointer", "An unexpected error occurred. Please try again.");
            return null;
        } catch (DataAccessException e) {
            // Handle database access issues
            br.reject("error.database.access", "A database error occurred. Please try again later.");
            return null;
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            br.reject("error.general", "An unexpected error occurred. Please contact support.");
            return null;
        }
    }


    @Override
    public void processRegistration(GymGoerViewModel gymgoer, UserCredentialsViewModel userCred) {
        // Save UserCredentials details
        UserCredentials userCredentialsEntity = new UserCredentials(
                userCred.getUsername(),
                userCred.getPassword(),
                userCred.getEmail(),
                LocalDate.now()
        );
        userCredentialsRepository.save(userCredentialsEntity);


        // Save GymGoer details
        GymGoer gymGoerEntity = new GymGoer(
                gymgoer.getFirstName(),
                gymgoer.getLastName(),
                gymgoer.getGender(),
                gymgoer.getAddress(),
                userCredentialsEntity
        );
        gymGoerRepository.save(gymGoerEntity);


    }

}
