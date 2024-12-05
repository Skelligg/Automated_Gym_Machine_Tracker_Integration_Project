package be.kdg.integration3.easyrep.model;

import be.kdg.integration3.easyrep.model.sessions.Session;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//we did not add lastGym as an attribute because the user needs to be present
@Entity
@Table(name = "GYM_GOER")
public class GymGoer {
    @Id
    private int userId;

    @Column(nullable = false, length = 32)
    private String firstName;

    @Column(nullable = false, length = 32)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 6)
    private Gender gender;

    @Column(nullable = false, length = 100)
    private String address;

    @OneToMany(mappedBy = "gymGoerId",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Routine> routines = new ArrayList<>();

    @OneToMany(mappedBy = "gymGoerId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Session> sessions = new ArrayList<>();


    @MapsId
    @OneToOne
    @JoinColumn(name = "user_Id")
    private UserCredentials userCredentials;


    public GymGoer() {
    }

    public GymGoer(String firstName, String lastName, Gender gender, String address, UserCredentials userCredentials) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.userCredentials = userCredentials;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "GymGoer{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", routines=" + routines +
                '}';
    }
}
