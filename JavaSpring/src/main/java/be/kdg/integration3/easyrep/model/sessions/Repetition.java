package be.kdg.integration3.easyrep.model.sessions;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "REPETITION")
public class Repetition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int repetitionId;
    private LocalDateTime startRep;
    private LocalDateTime endRep;

    @ManyToOne
    @JoinColumn(name = "machine_set_id", nullable = false)
    private MachineSet machineSet;

    protected Repetition() {}

    public Repetition(int repetitionId, LocalDateTime startRep, LocalDateTime endRep, MachineSet machineSet) {
        this.repetitionId = repetitionId;
        this.startRep = startRep;
        this.endRep = endRep;
        this.machineSet = machineSet;
    }

    public Repetition(MachineSet machineSet, LocalDateTime endRep, LocalDateTime startRep) {
        this.machineSet = machineSet;
        this.endRep = endRep;
        this.startRep = startRep;
    }


    public Repetition(LocalDateTime endRep, LocalDateTime startRep) {
        this.endRep = endRep;
        this.startRep = startRep;
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

    public MachineSet getMachineSet() {
        return machineSet;
    }

    public void setMachineSet(MachineSet machineSet) {
        this.machineSet = machineSet;
    }
}
