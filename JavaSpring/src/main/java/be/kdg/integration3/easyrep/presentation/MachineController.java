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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/GymOwner/machines")
public class MachineController {
    private final Logger logger = LoggerFactory.getLogger(MachineController.class);

    private MachineService machineService;
    private ArduinoService arduinoService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping
    public String getMachineView(Model model){
        logger.info("get Machine view");
        List<Machine> machines = machineService.findAllMachines();
        model.addAttribute("machines", machines);
        return "GymOwner/machines-overview";
    }

    /*
    @GetMapping("/add")
    public String showAddMachine(Model model){
        logger.info("Accessing page to create new Machine");
        return "Machines/addMachine";
    }

    @GetMapping("/detailMachine")
    public String viewMachine(@RequestParam("idMachine") int idMachine, Model model) {
        Machine machine = machineService.findMachineById(idMachine);
        logger.debug("View Machine: " + machine);
        model.addAttribute("machine", machine);
        return "Machines/detailMachine";
    }*/

    @GetMapping("/machineReview")
    public String viewReviewMachine(@RequestParam("idMachine") int idMachine, Model model) {
        Machine machine = machineService.findMachineById(idMachine);
        logger.debug("View Machine: " + machine);
        model.addAttribute("machine", machine);
        return "GymOwner/machine_review";
    }




}
