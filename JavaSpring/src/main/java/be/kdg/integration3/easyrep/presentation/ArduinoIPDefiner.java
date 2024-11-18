package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.repository.ArduinoRepositoryImpl;
import be.kdg.integration3.easyrep.service.ArduinoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/registerIP")
@RequestMapping("/")
public class ArduinoIPDefiner {
    private Logger logger = LoggerFactory.getLogger(ArduinoIPDefiner.class);
    public ArduinoService arduinoService;
//  database class

    // Constructor injection of ArduinoService
    @Autowired
    public ArduinoIPDefiner(ArduinoService arduinoService) {
        this.arduinoService = arduinoService;
    }

    //adding checking id with database
    @GetMapping("/registerIP")
    public void registerIP(@RequestParam String id, @RequestParam String arduinoIP) {
        try {
            arduinoService.setArduinoInfo(id, arduinoIP);
            logger.info("Registered IP {} for ID: {}", arduinoService.getIpAddress(id), id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
