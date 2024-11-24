package be.kdg.integration3.easyrep.model.sessions;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.Routine;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoutineSession {
    private int id;
    private LocalDateTime startSession;
    private LocalDateTime endSession;
    private String status;
    private List<MachineSet> machineSet = new ArrayList<>();

    public RoutineSession() {
    }

    public RoutineSession(LocalDateTime startSession, LocalDateTime endSession, String status) {
        this.startSession = startSession;
        this.endSession = endSession;
        this.status = status;
    }

    public RoutineSession(LocalDateTime startSession, LocalDateTime endSession, String status, List<MachineSet> machineSet) {
        this.startSession = startSession;
        this.endSession = endSession;
        this.status = status;
        this.machineSet = machineSet;
    }

    public RoutineSession(int id, LocalDateTime startSession, LocalDateTime endSession, String status, List<MachineSet> machineSet) {
        this.id = id;
        this.startSession = startSession;
        this.endSession = endSession;
        this.status = status;
        this.machineSet = machineSet;
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


    public List<MachineSet> getMachineSet() {
        return machineSet;
    }

    public void setMachineSet(List<MachineSet> machineSet) {
        this.machineSet = machineSet;
    }

    @Override
    public String toString() {
        return "RoutineSession{" +
                "id=" + id +
                ", startDateTime=" + startSession +
                ", endDateTime=" + endSession +
                ", status='" + status + '\'' +
                ", machineSet=" + machineSet +
                '}';
    }
}
