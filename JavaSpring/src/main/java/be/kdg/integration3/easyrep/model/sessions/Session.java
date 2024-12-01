package be.kdg.integration3.easyrep.model.sessions;

import be.kdg.integration3.easyrep.model.Routine;
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
    @OneToMany(mappedBy = "session",cascade = CascadeType.ALL)
    private List<MachineSet> machineSet = new ArrayList<>();
    private LocalDateTime startSession;
    private LocalDateTime endSession;
    private String status;
    @OneToMany(mappedBy = "session",cascade = CascadeType.ALL)
    private List<Routine> routines;



    public Session() {
    }


    public Session(int id, List<MachineSet> machineSet, LocalDateTime startSession, LocalDateTime endSession, String status, List<Routine> routines) {
        this.id = id;
        this.machineSet = machineSet;
        this.startSession = startSession;
        this.endSession = endSession;
        this.status = status;
        this.routines = routines;
    }

    public Session(List<MachineSet> machineSet, LocalDateTime startSession, LocalDateTime endSession, String status, List<Routine> routines) {
        this.machineSet = machineSet;
        this.startSession = startSession;
        this.endSession = endSession;
        this.status = status;
        this.routines = routines;
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
        return "Session{" +
                "id=" + id +
                ", machineSet=" + machineSet +
                ", startSession=" + startSession +
                ", endSession=" + endSession +
                ", status='" + status + '\'' +
                ", routines=" + routines +
                '}';
    }
}
