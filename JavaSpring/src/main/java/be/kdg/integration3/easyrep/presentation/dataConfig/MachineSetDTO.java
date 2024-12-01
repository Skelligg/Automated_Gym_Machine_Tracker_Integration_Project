package be.kdg.integration3.easyrep.presentation.dataConfig;

import java.time.LocalDateTime;

public class MachineSetDTO {
    private double weight;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
