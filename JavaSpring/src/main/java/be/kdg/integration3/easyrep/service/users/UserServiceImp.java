package be.kdg.integration3.easyrep.service.users;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.GymStaff;
import be.kdg.integration3.easyrep.model.UserCredentials;
import be.kdg.integration3.easyrep.presentation.viewModels.UserLogin;
import be.kdg.integration3.easyrep.repository.users.GymGoerRepository;
import be.kdg.integration3.easyrep.repository.users.GymStaffRepository;
import be.kdg.integration3.easyrep.repository.users.UserCredentialsRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

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
    public UserCredentials getUserCredentialsByUsernameOrEmail(String usernameOrEmail) {
        return userCredentialsRepository.findByUsernameOrEmail(usernameOrEmail);
    }

    // Find gym staff by user ID
    @Override
    public GymStaff getGymStaffByUserId(int userId) {
        return gymStaffRepository.findByUserId(userId);
    }

    // Find gym goer by user ID
    @Override
    public GymGoer getGymGoerByUserId(int userId) {
        return gymGoerRepository.findByUserId(userId);
    }

    @Override
    public String attemptLogIn(UserLogin user, BindingResult br) {
        // Search by username or email
        UserCredentials userCheck = userCredentialsRepository.findByUsernameOrEmail(user.getUsernameOrEmail());

        if (userCheck == null) {
            br.rejectValue("usernameOrEmail", "username.not.found", "Username or email not found");
            return null; // Return null if validation fails
        }

        // Validate the password
        if (!userCheck.getPassword().equals(user.getPassword())) {
            br.rejectValue("password", "password.incorrect", "Incorrect password");
            return null; // Return null if validation fails
        }

        GymStaff gymStaff = gymStaffRepository.findByUserId(userCheck.getUserId());

        // Determine the redirection path
        if (gymStaff != null) {
            return "redirect:/" + gymStaff.getGymId() + "GymOwner";
        } else {
            return "redirect:/" + userCheck.getUsername() + "/home";
        }
    }

}
