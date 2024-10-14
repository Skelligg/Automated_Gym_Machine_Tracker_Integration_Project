package be.kdg.integration3.easyrep.model.sessions;

import java.time.LocalTime;

public class Set {
    private long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private int repCount;
    //private int weightCount; -- commented cause we're testing for the Tracer Bullet


    public Set(LocalTime startTime, LocalTime endTime, int repCount) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.repCount = repCount;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getRepCount() {
        return repCount;
    }

    public void setRepCount(int repCount) {
        this.repCount = repCount;
    }
}
