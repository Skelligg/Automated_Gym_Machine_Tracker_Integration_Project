package be.kdg.integration3.easyrep.model;

public class Arduino {
    private String ipAddress;
    private int id;

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
