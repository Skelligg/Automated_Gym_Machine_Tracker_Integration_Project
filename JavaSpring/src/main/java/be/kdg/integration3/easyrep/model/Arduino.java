package be.kdg.integration3.easyrep.model;

public class Arduino {
    private String ipAddress;
    private String id;

    public Arduino(String ipAddress, String id) {
        this.ipAddress = ipAddress;
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
