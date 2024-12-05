package be.kdg.integration3.easyrep.presentation.viewModels;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class UserCredentialsViewModel {
    private int userId; // Assumes it's auto-generated, not validated here

    @NotBlank(message = "Userame is required")
    @Size(min = 4, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Confirm Password")
    private String confirmPassword;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid format")
    private String email;

    private GymGoerViewModel gymgoer;

    public UserCredentialsViewModel(GymGoerViewModel gymgoer) {
        this.gymgoer = gymgoer;
    }

    public GymGoerViewModel getGymgoer() {
        return gymgoer;
    }

    public void setGymgoer(GymGoerViewModel gymgoer) {
        this.gymgoer = gymgoer;
    }

    private LocalDate dateCreated; // Optional, typically auto-generated

    public UserCredentialsViewModel() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "UserCredentialsViewModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                ", gymgoer=" + gymgoer +
                '}';
    }
}
