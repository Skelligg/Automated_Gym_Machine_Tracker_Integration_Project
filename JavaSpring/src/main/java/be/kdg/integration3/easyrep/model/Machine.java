package be.kdg.integration3.easyrep.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "MACHINE")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_id")
    private int machineId;

    @OneToOne
    @JoinColumn(name = "arduino_id") // Make sure it references arduinoId in Arduino
    private Arduino arduino;  // No longer arduinoId, just arduino


    @ManyToOne
    @JoinColumn(name = "gym_id") //FK
    private Gym gym;


    @Column(nullable = false,length = 100)
    private String name;

    @Column(name = "last_time_checked")
    private LocalDateTime lastTimeChecked;

    @Column(name = "maintenance_period_in_days")
    private Integer maintenancePeriodInDays;

    public Machine() {
    }

    public Machine(int machineId, Gym gym, Arduino arduino, String name, LocalDateTime lastTimeChecked, Integer maintenancePeriodInDays) {
        this.machineId = machineId;
        this.gym = gym;
        this.arduino = arduino;
        this.name = name;
        this.lastTimeChecked = lastTimeChecked;
        this.maintenancePeriodInDays = maintenancePeriodInDays;
    }

    public Machine(Gym gym, Arduino arduino, String name, LocalDateTime lastTimeChecked, Integer maintenancePeriodInDays) {
        this.gym = gym;
        this.arduino = arduino;
        this.name = name;
        this.lastTimeChecked = lastTimeChecked;
        this.maintenancePeriodInDays = maintenancePeriodInDays;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getImageUrl(){
        String imageUrl = "/images/machinesPics/" + getName().replaceAll(" ", "").concat(".gif");
        return imageUrl;
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

    public void setArduino(Arduino arduinoId) {
        this.arduino = arduinoId;
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

    public void setMaintenancePeriodInDays(Integer maintenancePeriodInDays) {
        this.maintenancePeriodInDays = maintenancePeriodInDays;
    }

    public Integer getMaintenancePeriodInDays() {
        return maintenancePeriodInDays;
    }
}
