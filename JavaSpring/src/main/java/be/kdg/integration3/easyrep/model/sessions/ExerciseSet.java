package be.kdg.integration3.easyrep.model.sessions;

import jakarta.persistence.*;

import java.time.LocalTime;
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

    private LocalTime startTime;
    private LocalTime endTime;
    private int setNumber;
    @Column(length = 50)
    private String previousSet;

    @Column(nullable = false)
    private double weightCount;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public ExerciseSet(){}

    public ExerciseSet(int setId, List<Repetition> repetitionId, LocalTime startTime, LocalTime endTime, String previousSet, double weightCount) {
        this.setId = setId;
        this.repetitionId = repetitionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.previousSet = previousSet;
        this.weightCount = weightCount;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
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
