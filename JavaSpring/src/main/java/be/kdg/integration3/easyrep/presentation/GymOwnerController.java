package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.*;
import be.kdg.integration3.easyrep.presentation.viewModels.MachineViewModel;
import be.kdg.integration3.easyrep.service.ArduinoService;
import be.kdg.integration3.easyrep.service.GymService;
import be.kdg.integration3.easyrep.service.MachineService;
import be.kdg.integration3.easyrep.service.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/gymhome")
public class GymOwnerController {


    private final Logger logger = LoggerFactory.getLogger(GymOwnerController.class);

    private UserService userService;
    private MachineService machineService;
    private ArduinoService arduinoService;
    private GymService gymService;

    public GymOwnerController(MachineService machineService, ArduinoService arduinoService, GymService gymService, UserService userService) {
        this.userService = userService;
        this.machineService = machineService;
        this.arduinoService = arduinoService;
        this.gymService = gymService;
    }

    @GetMapping("/{username}")
    public String getGymOwnerView(@PathVariable String username, Model model) {
        UserCredentials user = userService.getUserCredentialsByUsername(username);
        model.addAttribute("user", user);

        logger.info("get Machine view");
        return "GymOwner/index-owner";
    }

    @GetMapping("/{username}/machines")
    public String getMachineView(@PathVariable String username, @RequestParam(value = "idGym", required = false) Integer idGym, Model model) {
        UserCredentials user = userService.getUserCredentialsByUsername(username);
        model.addAttribute("user", user);

        logger.info("Loading machines overview from {}", user.getUsername());
        GymStaff userStaff = userService.getGymStaffByUserId(user.getUserId());

        List<Gym> gyms = userStaff.getGymId();
        model.addAttribute("gyms", gyms);

        if (idGym == null && !gyms.isEmpty()) {
            idGym = gyms.getFirst().getGymId();
        }

        // create a gym page should be call here if there is no gym's on the user
        if (idGym == null && gyms.isEmpty()) {
            return "GymOwner/index-owner"; //If there is no gyms yet get back to dashboard,
        }
        model.addAttribute("idGym", idGym);
        model.addAttribute("gymSelected", gymService.findGymById(idGym));
        model.addAttribute("machines", gymService.findGymById(idGym).getMachines());

        return "GymOwner/machines-overview";

    }


    @GetMapping("/{username}/machines/{gymID}/add")
    public String showAddMachine(@PathVariable String username,@PathVariable int gymID, Model model) {
        UserCredentials user = userService.getUserCredentialsByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("gym", gymService.findGymById(gymID));
        logger.info("Accessing page to create new Machine");
        //get out machines with ID from 1 to 20
        //create query to retrieve them
        List<Machine> ourMachines = machineService.findOurMachines();

        model.addAttribute("ourMachines", ourMachines);

        //model.addAttribute("arduino", arduinoService.findAllArduinos());
        model.addAttribute("machineViewModel", new MachineViewModel());
        return "GymOwner/machine_add";
    }


    @PostMapping("/{username}/machines/{gymID}/add")
    public String addMachine(@PathVariable String username, @PathVariable int gymID, @ModelAttribute("machineViewModel") MachineViewModel machineViewModel, Model model) {
        logger.info("Creating machine with ID '{}', Arduino IP '{}'", machineViewModel.getMachineId(), machineViewModel.getArduinoId());
        UserCredentials user = userService.getUserCredentialsByUsername(username);
        model.addAttribute("user", user);
        //find our machines by id
        Machine selectedMachine = machineService.findMachineById(machineViewModel.getMachineId());
        //retrieve the arduino, the arduino id has to be written and check if exist and then we get the arduino from it.
        Arduino arduino = new Arduino();
        selectedMachine.setArduino(arduino);
        selectedMachine.setGym(gymService.findGymById(gymID));
        //Gym gym, Arduino arduinoId, String name, LocalDateTime lastTimeChecked
        Machine newMachine = new Machine(selectedMachine.getGym(), arduino, selectedMachine.getName(), LocalDate.now().atStartOfDay());
        machineService.createMachine(newMachine);

        //add code to also create or access to the arduino ID

        //logger.info("Machine '{}' with Arduino '{}' added successfully", newMachine.getName(), arduino);

        return "redirect:/GymOwner/machines";
    }


}
