package be.kdg.integration3.easyrep.model;

import java.util.ArrayList;
import java.util.List;

public class GymStaff {
    private int userId;
    private int gymId;

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
}
