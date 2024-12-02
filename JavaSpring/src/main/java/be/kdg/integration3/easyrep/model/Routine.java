package be.kdg.integration3.easyrep.model;

import be.kdg.integration3.easyrep.model.sessions.Session;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROUTINE")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany
    private List<Session> sessionId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private GymGoer gymID;
    @OneToMany
    private List<Machine> machines = new ArrayList<>();
    @Column(nullable = false,length = 50)
    private String routineName;

    public Routine() {
    }

    public Routine(int id, List<Session> sessionId, GymGoer gymID, List<Machine> machines, String routineName) {
        this.id = id;
        this.sessionId = sessionId;
        this.gymID = gymID;
        this.machines = machines;
        this.routineName = routineName;
    }

    public Routine(List<Session> sessionId, GymGoer gymID, List<Machine> machines, String routineName) {
        this.sessionId = sessionId;
        this.gymID = gymID;
        this.machines = machines;
        this.routineName = routineName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Session> getSessionId() {
        return sessionId;
    }

    public void setSessionId(List<Session> sessionId) {
        this.sessionId = sessionId;
    }

    public GymGoer getGymID() {
        return gymID;
    }

    public void setGymID(GymGoer gymID) {
        this.gymID = gymID;
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

    @Override
    public String toString() {
        return "Routine{" +
                "id=" + id +
                ", sessionId=" + sessionId +
                ", gymID=" + gymID +
                ", machines=" + machines +
                ", routineName='" + routineName + '\'' +
                '}';
    }
}