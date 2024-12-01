package be.kdg.integration3.easyrep.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GYM_STAFF")
public class GymStaff {

    @Id
    @OneToOne
    private int userId;

    @ManyToOne
    @JoinColumn(name = "gym_id",nullable = false)
    private Gym gymId;

    protected GymStaff() {
    }

    public GymStaff(int userId, Gym gymId) {
        this.userId = userId;
        this.gymId = gymId;
    }

    public GymStaff(Gym gymId) {
        this.gymId = gymId;
    }

    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Gym getGymId() {
        return gymId;
    }

    public void setGymId(Gym gymId) {
        this.gymId = gymId;
    }
}
