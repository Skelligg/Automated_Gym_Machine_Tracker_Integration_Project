package be.kdg.integration3.easyrep.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="GYM")
public class Gym {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int gymId;
    private String name;
    private String location;
    private Date openedOn;

    @OneToMany(mappedBy = "gym",cascade = CascadeType.ALL)
    private List<GymStaff> gymStaff;
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL)
    private List<Machine> machines;


    protected Gym() {
    }

    public Gym(int gymId, String name, String location, Date openedOn, List<GymStaff> gymStaff, List<Machine> machines) {
        this.gymId = gymId;
        this.name = name;
        this.location = location;
        this.openedOn = openedOn;
        this.gymStaff = gymStaff;
        this.machines = machines;
    }

    public Gym(String name, String location, Date openedOn, List<GymStaff> gymStaff, List<Machine> machines) {
        this.name = name;
        this.location = location;
        this.openedOn = openedOn;
        this.gymStaff = gymStaff;
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

    public Date getOpenedOn() {
        return openedOn;
    }

    public void setOpenedOn(Date openedOn) {
        this.openedOn = openedOn;
    }

    public List<GymStaff> getGymStaff() {
        return gymStaff;
    }

    public void setGymStaff(List<GymStaff> gymStaff) {
        this.gymStaff = gymStaff;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }
}
