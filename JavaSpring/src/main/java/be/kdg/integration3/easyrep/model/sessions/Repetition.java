package be.kdg.integration3.easyrep.model.sessions;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "REPETITION")
public class Repetition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repetition_id")
    private int repetitionId;
    @Column(nullable = false, name = "start_rep")
    private LocalDateTime startRep;
    @Column(nullable = false, name = "end_rep")
    private LocalDateTime endRep;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_set_set_id")
    private ExerciseSet exerciseSet;

    public Repetition() {
    }

    public Repetition(int repetitionId, LocalDateTime startRep, LocalDateTime endRep, ExerciseSet exerciseSet) {
        this.repetitionId = repetitionId;
        this.startRep = startRep;
        this.endRep = endRep;
        this.exerciseSet = exerciseSet;
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

    public ExerciseSet getExerciseSet() {
        return exerciseSet;
    }

    public void setExerciseSet(ExerciseSet exerciseSet) {
        this.exerciseSet = exerciseSet;
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
