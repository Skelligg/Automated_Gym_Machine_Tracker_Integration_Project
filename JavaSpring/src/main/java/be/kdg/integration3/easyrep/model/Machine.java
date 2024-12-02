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
    private int machineId;
    @ManyToOne
    private Gym gymId;
    @OneToOne
    private Arduino arduinoId;
    @Column(nullable = false,length = 30)
    private String name;
    private LocalDateTime lastTimeChecked;

    public Machine() {
    }

    public Machine(int machineId, Gym gymId, Arduino arduinoId, String name, LocalDateTime lastTimeChecked) {
        this.machineId = machineId;
        this.gymId = gymId;
        this.arduinoId = arduinoId;
        this.name = name;
        this.lastTimeChecked = lastTimeChecked;
    }

    public Machine(Gym gymId, Arduino arduinoId, String name, LocalDateTime lastTimeChecked) {
        this.gymId = gymId;
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

    public Gym getGymId() {
        return gymId;
    }

    public void setGymId(Gym gymId) {
        this.gymId = gymId;
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
