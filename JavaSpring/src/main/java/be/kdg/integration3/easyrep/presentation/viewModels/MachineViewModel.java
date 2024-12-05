package be.kdg.integration3.easyrep.presentation.viewModels;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.model.MaintenanceAlarm;
import be.kdg.integration3.easyrep.model.sessions.MachineSet;

public class MachineViewModel {
    private int machineId;
    private String name;
    private MaintenanceAlarm alarm;
    private String imageAddress;
    private int arduino;

    public MachineViewModel() {
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


    public int getArduino() {
        return arduino;
    }

    public void setArduino(int arduino) {
        this.arduino = arduino;
    }
}
