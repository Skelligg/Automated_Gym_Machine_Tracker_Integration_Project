package be.kdg.integration3.easyrep.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registerIP")
public class ArduinoIPDefiner {
    private Logger logger = LoggerFactory.getLogger(ArduinoIPDefiner.class);
    private String id;
    private String arduinoIpAddress;
//  database class

    //adding checking id with database
    @GetMapping
    public void registerIP(@RequestParam String id, @RequestParam String arduinoIP) {
        this.arduinoIpAddress = arduinoIP;
    }


    public String getArduinoIpAddress() {
        return arduinoIpAddress;
    }

}
