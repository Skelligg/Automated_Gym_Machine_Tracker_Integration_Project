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
}
