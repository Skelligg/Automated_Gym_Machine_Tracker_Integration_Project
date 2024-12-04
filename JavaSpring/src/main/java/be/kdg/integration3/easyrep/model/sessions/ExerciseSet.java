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

    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(length = 50, name = "previous_set")
    private String previousSet;

    @Column(nullable = false, name = "weight_count")
    private double weightCount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_id",nullable = false)
    private Exercise exercise;

    public ExerciseSet(){}

    public ExerciseSet(int setId, List<Repetition> repetitionId, LocalDateTime startTime, LocalDateTime endTime, String previousSet, double weightCount, Exercise exercise) {
        this.setId = setId;
        this.repetitionId = repetitionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.previousSet = previousSet;
        this.weightCount = weightCount;
        this.exercise = exercise;
    }

    public ExerciseSet(double weightCount, String previousSet, LocalDateTime endTime, LocalDateTime startTime, List<Repetition> repetitionId, int setId) {
        this.weightCount = weightCount;
        this.previousSet = previousSet;
        this.endTime = endTime;
        this.startTime = startTime;
        this.repetitionId = repetitionId;
        this.setId = setId;
    }

    public ExerciseSet(double weightCount, String previousSet, LocalDateTime endTime, LocalDateTime startTime) {
        this.weightCount = weightCount;
        this.previousSet = previousSet;
        this.endTime = endTime;
        this.startTime = startTime;
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

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
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
