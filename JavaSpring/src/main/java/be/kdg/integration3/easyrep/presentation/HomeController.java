package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.service.SetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/")
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);
    public SetService setService;
    private String arduinoIpAddress;

    @Autowired
    public HomeController(SetService setService) {
        this.setService = setService;
    }

    public void setArduinoIpAddress(String arduinoIpAddress) {
        this.arduinoIpAddress = arduinoIpAddress;
    }

    @GetMapping
    public String getIndex(Model model){
        logger.debug("Trying to get Index");
        model.addAttribute("buttonText", "Start your set!");
        return "home";
    }

    @PostMapping("/sets")
    public String setsPage(Model model){
        logger.debug("PostMapping received, trying to get you to /sets");
        logger.debug("Accessing API C++");

        setService.emptyRepository();

        logger.debug("Trying to access API C++");
        //Trigger the Arduino
        //10.134.217.4 - campus gp
        //10.134.217.13 - campus pothoek
        String arduinoUrl = arduinoIpAddress + "/trigger";
        RestTemplate restTemplate = new RestTemplate();
        try {
            // Send an HTTP POST request to the Arduino
            String response = restTemplate.postForObject(arduinoUrl, null, String.class);
            logger.debug("Arduino Response: " + response);
            model.addAttribute("arduinoResponse", "Arduino Response: " + response);
            logger.debug("Sending API request");
        } catch (Exception e) {
            logger.error("Error triggering Arduino: " + e.getMessage());
            model.addAttribute("arduinoResponse", "Error triggering Arduino: " + e.getMessage());
        }

        logger.debug("API worked");


        return "redirect:sets";
    }

}
