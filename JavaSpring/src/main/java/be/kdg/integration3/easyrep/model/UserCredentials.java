package be.kdg.integration3.easyrep.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USER_CREDENTIAL")
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", insertable = false, updatable = false)
    private int userId;

    @Column(unique = true, nullable = false, length = 32)
    private String username;

    @Column(unique = false, nullable = false, length = 255)
    private String password;

    @Column(unique = true, nullable = false, length = 32)
    private String email;

    @Column(name = "date_created", nullable = false)
    private LocalDate dateCreated;

    public UserCredentials() {
    }

    public UserCredentials(String username, String password, String email, LocalDate dateCreated) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateCreated = dateCreated;
    }

    public int getUserId() {
        return userId;
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

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
