package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.service.ArduinoService;
import be.kdg.integration3.easyrep.service.SetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);
    public SetService setService;
    public ArduinoService arduinoService;

    @Autowired
    public HomeController(SetService setService, ArduinoService arduinoService) {
        this.setService = setService;
        this.arduinoService = arduinoService;
    }

    @GetMapping
    public String getIndex(Model model){
        logger.debug("Trying to get Index");
        model.addAttribute("buttonText", "Start your set!");
        return "home";
    }

    @PostMapping("/sets")
    public String setsPage(@RequestParam("deviceId") String deviceId, Model model){
        logger.debug("PostMapping received, trying to get you to /sets");
        logger.debug("Device id: {}",  deviceId);

//        setService.emptyRepository();
//
//        logger.debug("Trying to access API C++");
//        //Trigger the Arduino
//        String arduinoUrl = "http://" + arduinoService.getIpAddress(deviceId) + "/trigger";
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            // Send an HTTP POST request to the Arduino
//            logger.debug("Sending API request");
//            String response = restTemplate.postForObject(arduinoUrl, null, String.class);
//            logger.debug("Arduino Response: {}", response);
//            model.addAttribute("arduinoResponse", "Arduino Response: " + response);
//            logger.debug("API worked");
//        } catch (Exception e) {
//            logger.error("Error triggering Arduino: {}", e.getMessage());
//            model.addAttribute("arduinoResponse", "Error triggering Arduino: " + e.getMessage());
//        }

        return "redirect:sets";
    }

}
