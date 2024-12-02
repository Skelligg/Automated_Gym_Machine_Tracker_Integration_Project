package be.kdg.integration3.easyrep.model.sessions;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SESSION")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private LocalDateTime startSession;
    @Column(nullable = false)
    private LocalDateTime endSession;
    @Column(nullable = false)
    private String status;
    @OneToMany
    private List<Exercise> exercises = new ArrayList<>();

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

    public Session(int id, LocalDateTime startSession, LocalDateTime endSession, String status, List<Exercise> exercise) {
        this.id = id;
        this.startSession = startSession;
        this.endSession = endSession;
        this.status = status;
        this.exercises = exercise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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



    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", startDateTime=" + startSession +
                ", endDateTime=" + endSession +
                ", status='" + status + '\'' +
                ", exerciseSet=" + exercises +
                '}';
    }
}

