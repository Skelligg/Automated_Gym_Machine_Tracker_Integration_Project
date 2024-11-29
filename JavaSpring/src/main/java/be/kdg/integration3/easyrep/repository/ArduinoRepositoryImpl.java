package be.kdg.integration3.easyrep.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Repository
public class ArduinoRepositoryImpl {

    // this would retrieve from the database the MACHINE_ID and the ipaddress from the machine/arduino
    private Map<String, String> arduinoInfo = new HashMap<>(Map.of("row", ""));

//    private ArduinoRepositoryImpl() {
//        createArduinoInfo("Test-ID", "127.0.0.1");
//        createArduinoInfo("Test-ID2", "127.0.0.2");
//    }

    public void createArduinoInfo(String id, String ipAddress) {
        arduinoInfo.put(id, ipAddress);
    }

    // Getter for IP Address by ID
    public String getIpAddress(String id) {
        return arduinoInfo.get(id);
    }

    // Getter for all IDs
    public Set<String> getAllIds() {
        return arduinoInfo.keySet();
    }

    // Getter for all IP Addresses
    public Set<String> getAllIpAddresses() {
        return Set.copyOf(arduinoInfo.values());
    }

    public Map<String, String> getArduinoInfo() {
        return arduinoInfo;
    }

    public void setIpAddress(String id, String ipAddress) {
        //get id and info from database and check if the id exists in the database
        arduinoInfo.replace(id, ipAddress);
    }
}
