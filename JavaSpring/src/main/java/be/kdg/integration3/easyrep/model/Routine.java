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
    @Column(name = "routine_id")
    private int id;
    @OneToMany(mappedBy = "routine",cascade = CascadeType.ALL)
    private List<Session> sessionId;
    @ManyToOne
    @JoinColumn(name = "owner_user_Id",nullable = false)
    private GymGoer gymGoer;

    @ManyToMany(mappedBy = "routines")
    private List<Machine> machines;

    @Column(nullable = false,length = 50,name = "routine_name")
    private String routineName;

    public Routine() {
    }

    public Routine(int id, List<Session> sessionId, GymGoer gymGoer, List<Machine> machines, String routineName) {
        this.id = id;
        this.sessionId = sessionId;
        this.gymGoer = gymGoer;
        this.machines = machines;
        this.routineName = routineName;
    }

    public Routine(List<Session> sessionId, GymGoer gymGoer, List<Machine> machines, String routineName) {
        this.sessionId = sessionId;
        this.gymGoer = gymGoer;
        this.machines = machines;
        this.routineName = routineName;
    }

    public Routine(String routineName) {
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

    public GymGoer getGymGoer() {
        return gymGoer;
    }

    public void setGymGoer(GymGoer gymID) {
        this.gymGoer = gymID;
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
                ", gymID=" + gymGoer +
                ", machines=" + machines +
                ", routineName='" + routineName + '\'' +
                '}';
    }
}