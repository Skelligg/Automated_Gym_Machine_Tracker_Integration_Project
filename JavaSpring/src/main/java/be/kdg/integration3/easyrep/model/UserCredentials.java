package be.kdg.integration3.easyrep.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USER_CREDENTIAL")
@DiscriminatorColumn(name = "user_ID")
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",insertable = false, updatable = false)
    private int userId;
    @Column(unique = true, nullable = false, length = 32)
    private String username;
    @Column(unique = true, nullable = false, length = 32)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @OneToOne(mappedBy = "userCredentials",cascade = CascadeType.ALL)
    private GymGoer gymGoer;
    @OneToOne(mappedBy = "userCredentials",cascade = CascadeType.ALL)
    private GymStaff gymStaff;

    public UserCredentials() {
    }

    public UserCredentials(int userId, String username, String password, String email, LocalDate dateCreated, GymGoer gymGoer, GymStaff gymStaff) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateCreated = dateCreated;
        this.gymGoer = gymGoer;
        this.gymStaff = gymStaff;
    }

    public UserCredentials(int userId, String username, String password, String email, LocalDate dateCreated) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateCreated = dateCreated;
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

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public GymGoer getGymGoer() {
        return gymGoer;
    }

    public void setGymGoer(GymGoer gymGoer) {
        this.gymGoer = gymGoer;
    }

    public GymStaff getGymStaff() {
        return gymStaff;
    }

    public void setGymStaff(GymStaff gymStaff) {
        this.gymStaff = gymStaff;
    }
}
