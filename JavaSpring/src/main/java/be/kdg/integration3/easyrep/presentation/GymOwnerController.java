package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.*;
import be.kdg.integration3.easyrep.presentation.viewModels.MachineViewModel;
import be.kdg.integration3.easyrep.service.ArduinoService;
import be.kdg.integration3.easyrep.service.GenderAnalyticsService;
import be.kdg.integration3.easyrep.service.GymService;
import be.kdg.integration3.easyrep.service.MachineService;
import be.kdg.integration3.easyrep.service.users.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

@Controller
@RequestMapping("/gymhome")
public class GymOwnerController {


    private final Logger logger = LoggerFactory.getLogger(GymOwnerController.class);

    private UserService userService;
    private MachineService machineService;
    private ArduinoService arduinoService;
    private GymService gymService;
    private GenderAnalyticsService genderAnalyticsService;

    @Autowired
    public GymOwnerController(UserService userService, MachineService machineService, ArduinoService arduinoService, GymService gymService, GenderAnalyticsService genderAnalyticsService) {
        this.userService = userService;
        this.machineService = machineService;
        this.arduinoService = arduinoService;
        this.gymService = gymService;
        this.genderAnalyticsService = genderAnalyticsService;
    }

    @GetMapping("/{username}")
    public String getGymOwnerView(@PathVariable String username, Model model) {
        UserCredentials user = userService.getUserCredentialsByUsername(username);
        model.addAttribute("user", user);

        // Get the data for Machine Usage
        List<Gym> gyms = userService.getGymStaffByUserId(user.getUserId()).getGymId();
        int idGym = gyms.isEmpty() ? 0 : gyms.get(0).getGymId();
        if (!gyms.isEmpty()) {

            HashMap<String, Integer> topMachineUsage = new HashMap<>();
            if (machineService.findUsageOfTopMachines(idGym) != null) {
                topMachineUsage = machineService.findUsageOfTopMachines(idGym);
                logger.info("Machine usage data: {}", topMachineUsage);
            }
            model.addAttribute("machineUsage", topMachineUsage);
            List<String> machineUsageKeys = new ArrayList<>(topMachineUsage.keySet());
            List<Integer> machineUsageValues = new ArrayList<>(topMachineUsage.values());

            model.addAttribute("machineTopUsageKeys", machineUsageKeys);
            model.addAttribute("machineTopUsageValues", machineUsageValues);

            TreeMap<String, Integer> gymUsage = new TreeMap<>();
            if (gymService.findUsageOfGymPerHour(idGym) != null) {
                gymUsage = gymService.findUsageOfGymPerHour(idGym);
                logger.info("Machine usage data: {}", gymUsage);
            }
            model.addAttribute("gymUsage", gymUsage);
            List<String> gymUsageKeys = new ArrayList<>(gymUsage.keySet());
            List<Integer> gymUsageValues = new ArrayList<>(gymUsage.values());

            model.addAttribute("gymUsageKeys", gymUsageKeys);
            model.addAttribute("gymUsageValues", gymUsageValues);

            TreeMap<Integer, Integer> gymUsageMonthly = new TreeMap<>();
            if (gymService.findMonthlyUsageOfGymPerDay(idGym) != null) {
                gymUsageMonthly = gymService.findMonthlyUsageOfGymPerDay(idGym);
                logger.info("Machine usage data: {}", gymUsageMonthly);
            }
            model.addAttribute("gymUsageMonthly", gymUsageMonthly);
            List<Integer> gymUsageMonthlyKeys = new ArrayList<>(gymUsageMonthly.keySet());
            List<Integer> gymUsageMonthlyValues = new ArrayList<>(gymUsageMonthly.values());

            model.addAttribute("gymUsageMonthlyKeys", gymUsageMonthlyKeys);
            model.addAttribute("gymUsageMonthlyValues", gymUsageMonthlyValues);

        }

        JSONObject genderSummary = genderAnalyticsService.getGenderAnalyticsData();
        model.addAttribute("genderSummary", genderSummary.toMap());
        logger.info("Gender Summary Data: {}", genderSummary.toMap());

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


        return "redirect:/GymOwner/machines";
    }


}
