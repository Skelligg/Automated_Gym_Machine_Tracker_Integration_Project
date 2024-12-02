package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.service.ArduinoService;
import be.kdg.integration3.easyrep.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/GymOwner")
public class GymOwnerController {

    private final Logger logger = LoggerFactory.getLogger(GymOwnerController.class);

    private MachineService machineService;
    private ArduinoService arduinoService;


    @GetMapping
    public String getGymOwnerView(Model model){
        logger.info("get Machine view");
        return "GymOwner/index-owner";
    }


    public GymOwnerController(MachineService machineService, ArduinoService arduinoService) {
        this.machineService = machineService;
        this.arduinoService = arduinoService;
    }

    @GetMapping("/machines")
    public String getMachineView(Model model){
        logger.info("get Machine view");
        List<Machine> machines = machineService.getAllMachines();
        model.addAttribute("machines", machines);
        return "GymOwner/machines-overview";
    }

    @GetMapping("/machines/add")
    public String showAddMachine(Model model){
        logger.info("Accessing page to create new Machine");
        List<Machine> ourMachines = machineService.getAllOurMachines();
        model.addAttribute("ourMachines", ourMachines);
        return "GymOwner/machine_add";
    }

    @GetMapping("/machines/machineReview")
    public String viewReviewMachine(@RequestParam("idMachine") int idMachine, Model model) {
        Machine machine = machineService.findMachineById(idMachine);
        logger.debug("View Machine: " + machine);
        model.addAttribute("machine", machine);
        return "GymOwner/machine_review";
    }


    @PostMapping("/machines/add")
    public String addExercise(@RequestParam("machineID") int machineID,
                              @RequestParam("arduinoID") String arduinoID, Model model) {

        logger.info("Creating machine '{}' with arduino {}", machineID, arduinoID);
        Arduino arduino = new Arduino(arduinoID, arduinoService.getIpAddress(arduinoID));


        Machine machine = new Machine(machineService.findOurMachineById(machineID).getName(), machineService.findOurMachineById(machineID).getImageAddress(),
               arduino );

        arduinoService.createArduino(arduino);
        machineService.createMachine(machine);


        return "redirect:/GymOwner/machines";
    }
}
