package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.repository.ArduinoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArduinoService {
    public ArduinoRepository arduinoRepository;
    Logger logger = LoggerFactory.getLogger(ArduinoService.class);

    @Autowired
    public ArduinoService(ArduinoRepository arduinoRepository) {
        this.arduinoRepository = arduinoRepository;
    }


    public Arduino findArduinoById(int id) {
        logger.info("findArduinoById");
      return arduinoRepository.findById(id);
    }

    @Transactional
    public void createArduino(Arduino arduino) {
        logger.info("Creating arduino: " + arduino);
        arduinoRepository.save(arduino);
    }

    @Transactional
    public void deleteArduino(int id) {
        logger.info("Deleting arduino: " + id);
        arduinoRepository.deleteById(id);
    }


    //IDK ABOUT THIS
    public void setArduinoInfo(Arduino arduino) {
        try {
            arduinoRepository.save(arduino);
        } catch (Exception e) {
            logger.error("could not input the received data into the system {}", e.getMessage());
        }
    }


    // Getter for IP Address by ID // DO NOT KNOW ABOUT THIS
    public String getIpAddress(int id) {
        return arduinoRepository.findById(id).getIpAddress();
    }

    public Arduino getLastArduino(){
        return arduinoRepository.getLastArduino();
    }

//    public void getArduinoInfo() {
//        return arduinoRepository.getArduinoInfo();
//    }

}
