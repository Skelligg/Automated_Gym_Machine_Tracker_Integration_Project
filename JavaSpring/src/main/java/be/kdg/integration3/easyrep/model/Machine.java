package be.kdg.integration3.easyrep.model;

import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "MACHINE")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_id")
    private int machineId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gym_id",nullable = false) //FK
    private Gym gym;

    @OneToOne
    @JoinColumn(name = "arduino_id",unique = true) //FK
    private Arduino arduinoId;

    @Column(nullable = false,length = 30)
    private String name;

    @Column(name="last_time_checked")
    private LocalDateTime lastTimeChecked;

    @ManyToMany
    @JoinTable(name = "routine_machines",joinColumns = @JoinColumn(name = "machines_machine_id"), inverseJoinColumns = @JoinColumn(name = "routine_id"))
    private List<Routine> routines;


    public Machine() {
    }

    public Machine(int machineId, Gym gym, Arduino arduinoId, String name, LocalDateTime lastTimeChecked, List<Routine> routines) {
        this.machineId = machineId;
        this.gym = gym;
        this.arduinoId = arduinoId;
        this.name = name;
        this.lastTimeChecked = lastTimeChecked;
        this.routines = routines;
    }

    public Machine(int machineId, Gym gym, Arduino arduinoId, String name, LocalDateTime lastTimeChecked) {
        this.machineId = machineId;
        this.gym = gym;
        this.arduinoId = arduinoId;
        this.name = name;
        this.lastTimeChecked = lastTimeChecked;
    }

    public Machine(LocalDateTime lastTimeChecked, String name) {
        this.lastTimeChecked = lastTimeChecked;
        this.name = name;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public Arduino getArduinoId() {
        return arduinoId;
    }

    public void setArduinoId(Arduino arduinoId) {
        this.arduinoId = arduinoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastTimeChecked() {
        return lastTimeChecked;
    }

    public void setLastTimeChecked(LocalDateTime lastTimeChecked) {
        this.lastTimeChecked = lastTimeChecked;
    }

    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }
}
