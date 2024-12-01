package be.kdg.integration3.easyrep.model;

import be.kdg.integration3.easyrep.model.sessions.Session;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity(name = "ROUTINE")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routineId;
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session sessionId;
    @ManyToOne
    @JoinColumn(name = "owner_user_id", nullable = false)
    private GymGoer ownerUserId;
    @OneToMany
    @JoinColumn(name = "machine_id",nullable = false)
    private List<Machine> machines = new ArrayList<>();
    private String routineName;

    protected Routine() {
    }

    public Routine(int routineId, Session sessionId, GymGoer ownerUserId, List<Machine> machines, String routineName) {
        this.routineId = routineId;
        this.sessionId = sessionId;
        this.ownerUserId = ownerUserId;
        this.machines = machines;
        this.routineName = routineName;
    }

    public Routine(Session sessionId, GymGoer ownerUserId, List<Machine> machines, String routineName) {
        this.sessionId = sessionId;
        this.ownerUserId = ownerUserId;
        this.machines = machines;
        this.routineName = routineName;
    }


    public int getRoutineId() {
        return routineId;
    }

    public void setRoutineId(int routineId) {
        this.routineId = routineId;
    }

    public Session getSessionId() {
        return sessionId;
    }

    public void setSessionId(Session sessionId) {
        this.sessionId = sessionId;
    }

    public GymGoer getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(GymGoer ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }
}