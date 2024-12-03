package be.kdg.integration3.easyrep.model.sessions;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "REPETITION")
public class Repetition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int repetitionId;
    @Column(nullable = false)
    private LocalDateTime startRep;
    @Column(nullable = false)
    private LocalDateTime endRep;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private ExerciseSet exerciseSet;

    public Repetition() {
    }

    public Repetition(int repetitionId, LocalDateTime startRep, LocalDateTime endRep) {
        this.repetitionId = repetitionId;
        this.startRep = startRep;
        this.endRep = endRep;
    }

    public Repetition(LocalDateTime startRep, LocalDateTime endRep) {
        this.startRep = startRep;
        this.endRep = endRep;
    }

    public int getRepetitionId() {
        return repetitionId;
    }

    public void setRepetitionId(int repetitionId) {
        this.repetitionId = repetitionId;
    }

    public LocalDateTime getStartRep() {
        return startRep;
    }

    public void setStartRep(LocalDateTime startRep) {
        this.startRep = startRep;
    }

    public LocalDateTime getEndRep() {
        return endRep;
    }

    public void setEndRep(LocalDateTime endRep) {
        this.endRep = endRep;
    }

    @Override
    public String toString() {
        return "Repetition{" +
                "repetitionId=" + repetitionId +
                ", startRep=" + startRep +
                ", endRep=" + endRep +
                '}';
    }
}
