package be.kdg.integration3.easyrep.model;

import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "MACHINE")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_id")
    private int machineId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "arduino_id") //FK
    private Arduino arduinoId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "gym_id") //FK
    private Gym gym;


    @Column(nullable = false,length = 30)
    private String name;

    private LocalDateTime lastTimeChecked;


    public Machine() {
    }

    public Machine(int machineId, Gym gym, Arduino arduinoId, String name, LocalDateTime lastTimeChecked) {
        this.machineId = machineId;
        this.gym = gym;
        this.arduinoId = arduinoId;
        this.name = name;
        this.lastTimeChecked = lastTimeChecked;
    }

    public Machine(Gym gym, Arduino arduinoId, String name, LocalDateTime lastTimeChecked) {
        this.gym = gym;
        this.arduinoId = arduinoId;
        this.name = name;
        this.lastTimeChecked = lastTimeChecked;
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
}
