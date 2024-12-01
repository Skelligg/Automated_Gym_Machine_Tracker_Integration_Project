package be.kdg.integration3.easyrep.model.sessions;

import java.time.LocalDateTime;

public class ExerciseSet {
    private int setNumber;
    private int repCount;
    private double weightCount;
    private boolean isCompleted;
    private String setTime; //make this a LocalTime]
    private LocalDateTime startTime;
    private LocalDateTime endTime;

//    public ExerciseSet(int setNumber, int setTime, double weightCount, int repCount) {
//        this.setNumber = setNumber;
//        this.setTime = setTime;
//        this.weightCount = weightCount;
//        this.repCount = repCount;
//    }

    public ExerciseSet(){}


    public ExerciseSet(int setNumber, String setTime, int repCount, double weightCount) {
        this.setNumber = setNumber;
        this.setTime = setTime;
        this.repCount = repCount;
        this.weightCount = weightCount;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public int getRepCount() {
        return repCount;
    }

    public void setRepCount(int repCount) {
        this.repCount = repCount;
    }

    public double getWeightCount() {
        return weightCount;
    }

    public void setWeightCount(double weightCount) {
        this.weightCount = weightCount;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
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
}
