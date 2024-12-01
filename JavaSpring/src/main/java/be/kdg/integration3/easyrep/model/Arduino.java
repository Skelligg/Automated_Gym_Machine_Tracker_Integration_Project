package be.kdg.integration3.easyrep.model;

import jakarta.persistence.*;

@Entity
@Table(name="ARDUINO")
public class Arduino {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String ipAddress;

    @OneToOne(mappedBy = "arduino")
    private Machine machine;

    protected Arduino() {
    }

    public Arduino(int id, String ipAddress, Machine machine) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.machine = machine;
    }
    public Arduino(String ipAddress, int id) {
        this.ipAddress = ipAddress;
        this.id = id;
    }


    public Arduino(String ipAddress) {
        this.ipAddress = ipAddress;
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

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
