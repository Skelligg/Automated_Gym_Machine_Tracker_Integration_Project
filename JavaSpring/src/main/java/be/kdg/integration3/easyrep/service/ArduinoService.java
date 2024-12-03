package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.repository.ArduinoRepositoryImpl;
import jakarta.transaction.Transactional;
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


    public Arduino findArduinoById(int id) {
        logger.info("findArduinoById");
      return arduinoRepository.findById(id);
    }

    public String findByIpAddress(String name) {
        logger.info("Find arduino by ip address");
        return arduinoRepository.getIpAddress(name);
    }
    @Transactional
    public Arduino createArduino(Arduino arduino) {
        logger.info("Creating arduino: " + arduino);
        return arduinoRepository.createArduino(arduino);
    }
    @Transactional
    public void updateArduino(Arduino arduino) {
        logger.info("Updating arduino: " + arduino);
        arduinoRepository.updateArduino(arduino);
    }
    @Transactional
    public void deleteArduino(int id) {
        logger.info("Deleting arduino: " + id);
        arduinoRepository.deleteArduino(id);
    }


    //IDK ABOUT THIS
    public void setArduinoInfo(Arduino arduino) {
        try {
            arduinoRepository.createArduino(arduino);
        } catch (Exception e) {
            logger.error("could not input the received data into the system {}", e.getMessage());
        }
    }


    // Getter for IP Address by ID // DO NOT KNOW ABOUT THIS
    public String getIpAddress(String id) {
        return arduinoRepository.getIpAddress(id);
    }

//    public void getArduinoInfo() {
//        return arduinoRepository.getArduinoInfo();
//    }

}
