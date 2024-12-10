package be.kdg.integration3.easyrep.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ARDUINO")
public class Arduino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int arduinoId;
    @Column(nullable = false, length = 20)
    private String ipAddress;

    @OneToOne(mappedBy = "arduino")  // Define the reverse mapping to the 'arduino' field in Machine
    private Machine machine;

    public Arduino() {
    }

    public Arduino(String ipAddress, int arduinoId) {
        this.ipAddress = ipAddress;
        this.arduinoId = arduinoId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getId() {
        return arduinoId;
    }

    public void setId(int id) {
        this.arduinoId = id;
    }

    public int getArduinoId() {
        return arduinoId;
    }

    public void setArduinoId(int arduinoId) {
        this.arduinoId = arduinoId;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
