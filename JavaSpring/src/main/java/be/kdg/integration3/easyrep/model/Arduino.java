package be.kdg.integration3.easyrep.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ARDUINO")
public class Arduino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 20)
    private String ipAddress;

    public Arduino() {
    }

    public Arduino(String ipAddress, int id) {
        this.ipAddress = ipAddress;
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
