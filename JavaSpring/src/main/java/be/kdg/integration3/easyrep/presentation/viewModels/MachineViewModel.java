package be.kdg.integration3.easyrep.presentation.viewModels;

import be.kdg.integration3.easyrep.model.Arduino;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;

public class MachineViewModel {
    private int machineId;
    private String name;

    private String imageAddress;
    private MachineSet machineSetId;
    private Arduino arduino;

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
