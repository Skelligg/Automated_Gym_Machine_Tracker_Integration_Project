package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import be.kdg.integration3.easyrep.service.MachineSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


//gonna mix this class with "SET CONTROLLER API"
@Controller
@RequestMapping("/sets")
public class MachineSetController {

    private static Logger logger = LoggerFactory.getLogger(MachineSetController.class);

    private MachineSetService machineSetService;

    @Autowired
    public MachineSetController(MachineSetService machineSetService) {
        this.machineSetService = machineSetService;
    }

    @GetMapping
    public String getSetsView(Model model){
        logger.debug("getting SetsView");
        List<MachineSet> machineSets = machineSetService.getSets();
        logger.debug(machineSets.toString());
        model.addAttribute("machineSets", machineSets);
        return "GymGoer/sets";
    }
}
