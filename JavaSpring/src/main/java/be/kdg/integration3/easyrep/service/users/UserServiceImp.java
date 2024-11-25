package be.kdg.integration3.easyrep.service.users;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.GymStaff;
import be.kdg.integration3.easyrep.model.UserCredentials;
import be.kdg.integration3.easyrep.repository.users.GymGoerRepository;
import be.kdg.integration3.easyrep.repository.users.GymStaffRepository;
import be.kdg.integration3.easyrep.repository.users.UserCredentialsRepository;
import org.springframework.stereotype.Service;

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
    public UserCredentials getUserCredentialsById(int id) {
        return userCredentialsRepository.findById(id);
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
}
