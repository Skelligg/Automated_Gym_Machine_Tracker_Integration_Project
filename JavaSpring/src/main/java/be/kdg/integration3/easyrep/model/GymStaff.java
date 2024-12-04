package be.kdg.integration3.easyrep.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GYM_STAFF")
public class GymStaff  {

    @Id
    private int userId;

    @OneToMany(mappedBy = "gymStaff",cascade = CascadeType.ALL)
    private List<Gym> gymId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    private UserCredentials userCredentials;



    public GymStaff() {
    }

    public GymStaff(int userId, List<Gym> gymId, UserCredentials userCredentials) {
        this.userId = userId;
        this.gymId = gymId;
        this.userCredentials = userCredentials;
    }

    public GymStaff(int userId, List<Gym> gymId) {
        this.userId = userId;
        this.gymId = gymId;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Gym> getGymId() {
        return gymId;
    }

    public void setGymId(List<Gym> gymId) {
        this.gymId = gymId;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    @Override
    public String toString() {
        return "GymStaff{" +
                "userId=" + userId +
                ", gymId=" + gymId +
                '}';
    }
}
