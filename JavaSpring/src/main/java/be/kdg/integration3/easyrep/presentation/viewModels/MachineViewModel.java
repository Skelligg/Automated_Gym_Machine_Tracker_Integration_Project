package be.kdg.integration3.easyrep.presentation.viewModels;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.model.Gym;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class MachineViewModel {

    private int machineId;
    private Gym gym;
    private Arduino arduinoId;
    private String name;
    private LocalDateTime lastTimeChecked;


    public MachineViewModel() {
    }

    public MachineViewModel(int machineId, Gym gym, Arduino arduinoId, String name, LocalDateTime lastTimeChecked) {
        this.machineId = machineId;
        this.gym = gym;
        this.arduinoId = arduinoId;
        this.name = name;
        this.lastTimeChecked = lastTimeChecked;
    }

    public MachineViewModel(Arduino arduinoId, String name, LocalDateTime lastTimeChecked) {
        this.arduinoId = arduinoId;
        this.name = name;
        this.lastTimeChecked = lastTimeChecked;
    }

    public MachineViewModel(Gym gym, Arduino arduinoId, String name, LocalDateTime lastTimeChecked) {
        this.gym = gym;
        this.arduinoId = arduinoId;
        this.name = name;
        this.lastTimeChecked = lastTimeChecked;
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

    public Arduino getArduinoId() {
        return arduinoId;
    }

    public void setArduinoId(Arduino arduinoId) {
        this.arduinoId = arduinoId;
    }

    public String getImageUrl(){
        String imageUrl = "/image/" + getName().replaceAll(" ", "").concat(".gif");
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastTimeChecked() {
        return lastTimeChecked;
    }

    public void setLastTimeChecked(LocalDateTime lastTimeChecked) {
        this.lastTimeChecked = lastTimeChecked;
    }
}
