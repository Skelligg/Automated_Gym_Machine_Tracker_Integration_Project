package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.service.ArduinoService;
import be.kdg.integration3.easyrep.service.routines.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/machines")
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
        List<Machine> machines = machineService.getAllMachines();
        model.addAttribute("machines", machines);
        return "Machines/machines";
    }

    @GetMapping("/add")
    public String showAddMachine(Model model){
        logger.info("Accessing page to create new Machine");

        return "Machines/addMachine";
    }


}
