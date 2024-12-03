package be.kdg.integration3.easyrep.model.sessions;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "EXERCISE_SET")
public class ExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "set_id")
    private int setId;

    @OneToMany(mappedBy = "exerciseSet", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Repetition> repetitionId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Column(length = 50)
    private String previousSet;

    @Column(nullable = false)
    private double weightCount;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public ExerciseSet(){}

    public ExerciseSet(int setId, List<Repetition> repetitionId, LocalDateTime startTime, LocalDateTime endTime, String previousSet, double weightCount) {
        this.setId = setId;
        this.repetitionId = repetitionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.previousSet = previousSet;
        this.weightCount = weightCount;
    }

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    public List<Repetition> getRepetitionId() {
        return repetitionId;
    }

    public void setRepetitionId(List<Repetition> repetitionId) {
        this.repetitionId = repetitionId;
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

    public double getWeightCount() {
        return weightCount;
    }

    public void setWeightCount(double weightCount) {
        this.weightCount = weightCount;
    }

    @Override
    public String toString() {
        return "ExerciseSet{" +
                "setId=" + setId +
                ", repetitionId=" + repetitionId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", previousSet='" + previousSet + '\'' +
                ", weightCount=" + weightCount +
                '}';
    }
}
