package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.repository.ArduinoRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ArduinoService {
    public ArduinoRepositoryImpl arduinoRepository;
    Logger logger = LoggerFactory.getLogger(ArduinoService.class);

    @Autowired
    public ArduinoService(ArduinoRepositoryImpl arduinoRepository) {
        this.arduinoRepository = arduinoRepository;
    }


    public Arduino createArduino(Arduino arduino) {
        logger.info("Creating a machine {}", arduino);
        arduinoRepository.createArduino(arduino);
        return arduino;
    }



    public void setArduinoInfo(String id, String ipAddress) {
        try {
            arduinoRepository.setIpAddress(id, ipAddress);
        } catch (Exception e) {
            logger.error("could not input the received data into the system {}", e.getMessage());
        }
    }


    // Getter for IP Address by ID
    public String getIpAddress(String id) {
        return arduinoRepository.getIpAddress(id);
    }

    public Map<String, String> getArduinoInfo() {
        return arduinoRepository.getArduinoInfo();
    }

}
