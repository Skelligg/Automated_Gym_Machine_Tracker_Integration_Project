package be.kdg.integration3.easyrep.model;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MACHINE")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int machineId;
    @ManyToOne
    @JoinColumn(name = "gym_id",nullable = false)
    private Gym gym;
    @OneToOne
    @JoinColumn(name = "arduino_id",nullable = false)
    private Arduino arduino;
    private String name;
    private Date lastTimeChecked;


    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL)
    private List<MachineSet> machineSets;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL)
    private List<Routine> routines;


    protected Machine() {
    }


    public Machine(String name) {
        this.name = name;
    }

    public Machine(int machineId, Gym gym, Arduino arduino, String name, Date lastTimeChecked, List<MachineSet> machineSets, List<Routine> routines) {
        this.machineId = machineId;
        this.gym = gym;
        this.arduino = arduino;
        this.name = name;
        this.lastTimeChecked = lastTimeChecked;
        this.machineSets = machineSets;
        this.routines = routines;
    }

    public Machine(Gym gym, Arduino arduino, String name, Date lastTimeChecked, List<MachineSet> machineSets, List<Routine> routines) {
        this.gym = gym;
        this.arduino = arduino;
        this.name = name;
        this.lastTimeChecked = lastTimeChecked;
        this.machineSets = machineSets;
        this.routines = routines;
    }


    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public Arduino getArduino() {
        return arduino;
    }

    public void setArduino(Arduino arduino) {
        this.arduino = arduino;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastTimeChecked() {
        return lastTimeChecked;
    }

    public void setLastTimeChecked(Date lastTimeChecked) {
        this.lastTimeChecked = lastTimeChecked;
    }

    public List<MachineSet> getMachineSets() {
        return machineSets;
    }

    public void setMachineSets(List<MachineSet> machineSets) {
        this.machineSets = machineSets;
    }

    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }
}
