package be.kdg.integration3.easyrep.service.users;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.GymStaff;
import be.kdg.integration3.easyrep.model.UserCredentials;

public interface UserService {
    // Add a new user
    void addUserCredentials(UserCredentials userCredentials);

    // Add gym staff
    void addGymStaff(GymStaff gymStaff);

    // Add gym goer
    void addGymGoer(GymGoer gymGoer);

    // Find user credentials by ID
    UserCredentials getUserCredentialsById(int id);

    // Find gym staff by user ID
    GymStaff getGymStaffByUserId(int userId);

    // Find gym goer by user ID
    GymGoer getGymGoerByUserId(int userId);
}
