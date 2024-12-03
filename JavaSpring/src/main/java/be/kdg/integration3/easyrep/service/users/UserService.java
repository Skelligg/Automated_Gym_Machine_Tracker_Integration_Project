package be.kdg.integration3.easyrep.service.users;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.GymStaff;
import be.kdg.integration3.easyrep.model.UserCredentials;
import be.kdg.integration3.easyrep.presentation.viewModels.GymGoerViewModel;
import be.kdg.integration3.easyrep.presentation.viewModels.UserCredentialsViewModel;
import be.kdg.integration3.easyrep.presentation.viewModels.UserLogin;
import org.springframework.validation.BindingResult;

public interface UserService {
    // Add a new user
    void addUserCredentials(UserCredentials userCredentials);

    // Add gym staff
    void addGymStaff(GymStaff gymStaff);

    // Add gym goer
    void addGymGoer(GymGoer gymGoer);

    // Find user credentials by ID
    UserCredentials getUserCredentialsByUsernameOrEmail(String usernameOrEmail);

    // Find gym staff by user ID
    GymStaff getGymStaffByUserId(int userId);

    // Find gym goer by user ID
    GymGoer getGymGoerByUserId(int userId);

    String attemptLogIn(UserLogin user, BindingResult br);

    void processRegistration(GymGoerViewModel gymgoer, UserCredentialsViewModel userCred);
}



