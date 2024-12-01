package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.service.ArduinoService;
import be.kdg.integration3.easyrep.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/GymOwner")
public class GymOwnerController {

    private final Logger logger = LoggerFactory.getLogger(MachineController.class);

    @GetMapping
    public String getMachineView(Model model){
        logger.info("get Machine view");
        return "GymOwner/index-owner";
    }
}
