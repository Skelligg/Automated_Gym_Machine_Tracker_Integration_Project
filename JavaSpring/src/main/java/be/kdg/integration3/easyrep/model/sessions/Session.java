package be.kdg.integration3.easyrep.model.sessions;

import be.kdg.integration3.easyrep.model.GymGoer;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SESSION")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="session_id")
    private int session_id;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Exercise> exercises = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = false)
    private GymGoer gymGoerId;

    @Column
    private LocalDateTime startSession;

    @Column
    private LocalDateTime endSession;

    @Column
    private String status;



    public Session() {
    }

    public Session(LocalDateTime startSession, LocalDateTime endSession, String status) {
        this.startSession = startSession;
        this.endSession = endSession;
        this.status = status;
    }

    public Session(LocalDateTime startSession, LocalDateTime endSession, String status, List<Exercise> exercise) {
        this.startSession = startSession;
        this.endSession = endSession;
        this.status = status;
        this.exercises = exercise;
    }

    public Session(int session_id, LocalDateTime startSession, LocalDateTime endSession, String status, List<Exercise> exercise) {
        this.session_id = session_id;
        this.startSession = startSession;
        this.endSession = endSession;
        this.status = status;
        this.exercises = exercise;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int id) {
        this.session_id = id;
    }

    public LocalDateTime getStartSession() {
        return startSession;
    }

    public void setStartSession(LocalDateTime startSession) {
        this.startSession = startSession;
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public void setEndSession(LocalDateTime endSession) {
        this.endSession = endSession;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public GymGoer getGymGoerId() {
        return gymGoerId;
    }

    public void setGymGoerId(GymGoer gymGoerId) {
        this.gymGoerId = gymGoerId;
    }


    @Override
    public String toString() {
        return "Session{" +
                "id=" + session_id +
                ", startDateTime=" + startSession +
                ", endDateTime=" + endSession +
                ", status='" + status + '\'' +
                ", exerciseSet=" + exercises +
                '}';
    }
}

