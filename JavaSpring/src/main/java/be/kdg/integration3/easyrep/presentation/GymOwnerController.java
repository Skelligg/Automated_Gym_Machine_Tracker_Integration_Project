package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.presentation.viewModels.MachineViewModel;
import be.kdg.integration3.easyrep.service.ArduinoService;
import be.kdg.integration3.easyrep.service.GymService;
import be.kdg.integration3.easyrep.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/GymOwner")
public class GymOwnerController {


    private final Logger logger = LoggerFactory.getLogger(GymOwnerController.class);

    private MachineService machineService;
    private ArduinoService arduinoService;
    private GymService gymService;


    @GetMapping
    public String getGymOwnerView(Model model) {
        logger.info("get Machine view");
        return "GymOwner/index-owner";
    }


    public GymOwnerController(MachineService machineService, ArduinoService arduinoService, GymService gymService) {
        this.machineService = machineService;
        this.arduinoService = arduinoService;
        this.gymService = gymService;
    }

    @GetMapping("/machines")
    public String getMachineView(Model model) {
        logger.info("Loading default machines overview");
        List<Machine> machines = machineService.findAllMachines();
        //has to be find all the machines by user
        model.addAttribute("machines", machines);
        return "GymOwner/machines-overview";

    }


    @GetMapping("/machines/add")
    public String showAddMachine(Model model) {
        logger.info("Accessing page to create new Machine");
        //get out machines with ID from 1 to 20
        //create query to retrieve them
        List<Machine> ourMachines = machineService.findOurMachines();

        model.addAttribute("ourMachines", ourMachines);

        //model.addAttribute("arduino", arduinoService.findAllArduinos());
        model.addAttribute("gym", gymService.findAllGyms());
        model.addAttribute("machineViewModel", new MachineViewModel());
        return "GymOwner/machine_add";
    }

    @GetMapping("/machines/machineReview")
    public String viewReviewMachine(@RequestParam("idMachine") Integer idMachine, Model model) {
        Machine machine = machineService.findMachineById(idMachine);
        logger.debug("View Machine: " + machine);
        model.addAttribute("machine", machine);
        return "GymOwner/machine_review";
    }


   /* @PostMapping("/machines/add")
    public String addExercise(@ModelAttribute MachineViewModel machineViewModel, @RequestParam("selectedMachineIds") int selectedMachineIds,
                              @RequestParam("arduinoID") String arduinoID, Model model) {

       logger.info("Creating machine '{}' with arduino {}", selectedMachineIds, arduinoID);
        Arduino arduino = new Arduino(arduinoID, arduinoService.getIpAddress(arduinoID));


        Machine machine = new Machine(machineService.findOurMachineById(selectedMachineIds).getName(), machineService.findOurMachineById(selectedMachineIds).getImageAddress(),
                arduino);

        arduinoService.createArduino(arduino);
        machineService.createMachine(machine);


        return "redirect:/GymOwner/machines";
    }*/

    @PostMapping("/machines/add")
    public String addMachine(@ModelAttribute("machineViewModel") MachineViewModel machineViewModel, Model model) {
        logger.info("Creating machine with ID '{}', Arduino IP '{}'", machineViewModel.getMachineId(), machineViewModel.getArduinoId());
        //find our machines by id
        Machine selectedMachine = machineService.findMachineById(machineViewModel.getMachineId());

        if (selectedMachine == null) {
            logger.error("Machine with ID '{}' not found", machineViewModel.getMachineId());
            return "redirect:/GymOwner/machines/add?error=machineNotFound";
        }

        //retrieve the arduino, the arduino id has to be written and check if exist and then we get the arduino from it.
        //Arduino arduino = machineViewModel.getArduino();
        //selectedMachine.setArduino(arduino);
        //Machine newMachine = new Machine(selectedMachine.getName(), selectedMachine.getImageAddress(), arduino);
        //machineService.createMachine(newMachine);

        //add code to also create or access to the arduino ID

        //logger.info("Machine '{}' with Arduino '{}' added successfully", newMachine.getName(), arduino);

        return "redirect:/GymOwner/machines";
    }


}
