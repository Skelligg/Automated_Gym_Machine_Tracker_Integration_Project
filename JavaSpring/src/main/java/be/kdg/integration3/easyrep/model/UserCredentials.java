package be.kdg.integration3.easyrep.model;


import java.time.LocalDate;

public class UserCredentials {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String gym;
    private LocalDate dateCreated;

    public UserCredentials(int userId, String username, String password, String email, String gym, LocalDate dateCreated) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gym = gym;
        this.dateCreated = dateCreated;
    }

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

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}
