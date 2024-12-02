package be.kdg.integration3.easyrep.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//we did not add lastGym as an attribute because the user needs to be present
@Entity
@Table(name = "GYM_GOER")
public class GymGoer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(nullable = false, length = 50)
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = false, length = 50)
    private String phone;
    @OneToMany(mappedBy = "gym_goer",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Routine> routines = new ArrayList<>();

    public GymGoer() {
    }

    public GymGoer(int userId, String firstName, String lastName, Gender gender, String address, String phone, List<Routine> routines) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.routines = routines;
    }

    public GymGoer(String firstName, String lastName, Gender gender, String address, String phone, List<Routine> routines) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.routines = routines;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }

    @Override
    public String toString() {
        return "GymGoer{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", routines=" + routines +
                '}';
    }
}
