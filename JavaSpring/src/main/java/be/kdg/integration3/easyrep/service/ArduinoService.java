package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import be.kdg.integration3.easyrep.repository.ArduinoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ArduinoService {

    public ArduinoRepositoryImpl arduinoRepository;

    public ArduinoService(ArduinoRepositoryImpl arduinoRepository) {
        this.arduinoRepository = arduinoRepository;
    }


    public void setArduinoInfo(String id, String ipAddress) {
        try{
            arduinoRepository.setIpAddress(id, ipAddress);
        } catch (Error e){
            System.out.println("whatever" + e.getMessage());
        }
    }

    // Getter for IP Address by ID
    public String getIpAddress(String id) {
        return arduinoRepository.getIpAddress(id);
    }

    public Map<String, String> getArduinoInfo(){
        return arduinoRepository.getArduinoInfo();
    }

}
