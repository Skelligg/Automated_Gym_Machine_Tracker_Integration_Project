package be.kdg.integration3.easyrep.model;

import java.util.Date;
import java.util.List;

public class Gym {
    private String name;
    private int gymId;
    private String location;
    private Date startDate;
    private List<Machine> machines;

    public Gym(String name, int gymId, String location, Date startDate, List<Machine> machines) {
        this.name = name;
        this.gymId = gymId;
        this.location = location;
        this.startDate = startDate;
        this.machines = machines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
