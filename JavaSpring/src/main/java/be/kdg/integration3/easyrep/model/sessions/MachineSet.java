package be.kdg.integration3.easyrep.model.sessions;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.sessions.Session;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Table
@Entity(name = "MACHINE_SET")
public class MachineSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int setId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id", nullable = false)
    private Session sessionId;

    @OneToMany(mappedBy = "machine_set", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Repetition> repetitions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "machine_id",nullable = false)
    private Machine machine;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String previousSet;
    private double weight;



    public MachineSet() {
    }

    public MachineSet(LocalDateTime endTime, LocalDateTime startTime, double weight) {
        this.endTime = endTime;
        this.startTime = startTime;
        this.weight = weight;
    }

    public MachineSet(int setId, Session sessionId, List<Repetition> repetitions, Machine machine, LocalDateTime startTime, LocalDateTime endTime, String previousSet, double weight) {
        this.setId = setId;
        this.sessionId = sessionId;
        this.repetitions = repetitions;
        this.machine = machine;
        this.startTime = startTime;
        this.endTime = endTime;
        this.previousSet = previousSet;
        this.weight = weight;
    }

    public MachineSet(Session sessionId, List<Repetition> repetitions, Machine machine, LocalDateTime startTime, LocalDateTime endTime, String previousSet, double weight) {
        this.sessionId = sessionId;
        this.repetitions = repetitions;
        this.machine = machine;
        this.startTime = startTime;
        this.endTime = endTime;
        this.previousSet = previousSet;
        this.weight = weight;
    }

    public MachineSet(LocalDateTime startTime, LocalDateTime endTime, String previousSet, double weight) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.previousSet = previousSet;
        this.weight = weight;
    }

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    public Session getSessionId() {
        return sessionId;
    }

    public void setSessionId(Session sessionId) {
        this.sessionId = sessionId;
    }

    public List<Repetition> getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(List<Repetition> repetitions) {
        this.repetitions = repetitions;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getPreviousSet() {
        return previousSet;
    }

    public void setPreviousSet(String previousSet) {
        this.previousSet = previousSet;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
