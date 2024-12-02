package be.kdg.integration3.easyrep.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GYM_GOER")
public class GymStaff  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private int gymId;

    public GymStaff() {
    }

    public GymStaff(int userId, int gymId) {
        this.userId = userId;
        this.gymId = gymId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    @Override
    public String toString() {
        return "GymStaff{" +
                "userId=" + userId +
                ", gymId=" + gymId +
                '}';
    }
}
