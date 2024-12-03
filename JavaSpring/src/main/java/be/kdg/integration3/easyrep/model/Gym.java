package be.kdg.integration3.easyrep.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "GYM")
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gym_id")
    private int gymId;

    @Column(nullable = false, length = 32)
    private String name;

    @Column(nullable = false, length = 50)
    private String location;
    private LocalDateTime openedOn;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Machine> machines;

    @ManyToOne(fetch = FetchType.EAGER)
    private GymStaff gymStaff;


    public Gym() {
    }

    public Gym(int gymId, String name, String location, LocalDateTime openedOn, List<Machine> machines) {
        this.gymId = gymId;
        this.name = name;
        this.location = location;
        this.openedOn = openedOn;
        this.machines = machines;
    }

    public Gym(String name, String location, LocalDateTime openedOn, List<Machine> machines) {
        this.name = name;
        this.location = location;
        this.openedOn = openedOn;
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

    public LocalDateTime getOpenedOn() {
        return openedOn;
    }

    public void setOpenedOn(LocalDateTime openedOn) {
        this.openedOn = openedOn;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public void addMachine(Machine machine) {
        machines.add(machine);
    }

    @Override
    public String toString() {
        return "Gym{" +
                "gymId=" + gymId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", openedOn=" + openedOn +
                ", machines=" + machines +
                '}';
    }
}
