package be.kdg.integration3.easyrep.model;

import be.kdg.integration3.easyrep.model.sessions.Set;

import java.util.Date;

public class Machine {
    private int machineId;
    private String name;
    private MaintenanceAlarm alarm;
    private String imageAddress;
    private Set setId;
    private Arduino arduino;

    public void alarmSet() {
        alarm.alarmSet();
    };

    public Machine(int machineId, String name, MaintenanceAlarm alarm, String imageAddress, Set setId, Arduino arduino) {
        this.machineId = machineId;
        this.name = name;
        this.alarm = alarm;
        this.imageAddress = imageAddress;
        this.setId = setId;
        this.arduino = arduino;

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

    public Set getSetId() {
        return setId;
    }

    public void setSetId(Set setId) {
        this.setId = setId;
    }

    public Arduino getArduino() {
        return arduino;
    }

    public void setArduino(Arduino arduino) {
        this.arduino = arduino;
    }

}
