package be.kdg.integration3.easyrep.model;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;

public class Machine {
    private int machineId;
    private String name;
    private MaintenanceAlarm alarm;
    private String imageAddress;
    private MachineSet machineSetId;
    private Arduino arduino;

    public void alarmSet() {
        alarm.alarmSet();
    };

    public Machine(int machineId, String name, String imageAddress, MachineSet machineSetId, Arduino arduino) {
        this.machineId = machineId;
        this.name = name;
        this.imageAddress = imageAddress;
        this.machineSetId = machineSetId;
        this.arduino = arduino;
    }

    public Machine(int machineId, String name, String imageAddress) {
        this.machineId = machineId;
        this.name = name;
        this.imageAddress = imageAddress;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MaintenanceAlarm getAlarm() {
        return alarm;
    }

    public void setAlarm(MaintenanceAlarm alarm) {
        this.alarm = alarm;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public MachineSet getSetId() {
        return machineSetId;
    }

    public void setSetId(MachineSet machineSetId) {
        this.machineSetId = machineSetId;
    }

    public Arduino getArduino() {
        return arduino;
    }

    public void setArduino(Arduino arduino) {
        this.arduino = arduino;
    }

}
