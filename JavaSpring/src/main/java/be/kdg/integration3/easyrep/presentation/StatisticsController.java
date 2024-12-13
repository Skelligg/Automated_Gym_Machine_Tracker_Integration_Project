package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.UserCredentials;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.model.sessions.Session;
import be.kdg.integration3.easyrep.service.ExerciseSetService;
import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.service.GymService;
import be.kdg.integration3.easyrep.service.MachineService;
import be.kdg.integration3.easyrep.service.session.ExerciseService;
import be.kdg.integration3.easyrep.service.users.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/")
public class StatisticsController {
    private final Logger logger = LoggerFactory.getLogger(StatisticsController.class);
    private MachineService machineService;
    private final ExerciseSetService exerciseSetService;
    private final ExerciseService exerciseService;
    private final UserService userService;
    private final GymService gymService;


    @Autowired
    public StatisticsController(ExerciseSetService exerciseSetService, MachineService machineService, ExerciseService exerciseService, UserService userService, GymService gymService) {
        this.machineService = machineService;
        this.exerciseSetService = exerciseSetService;
        this.exerciseService = exerciseService;
        this.userService = userService;
        this.gymService = gymService;
    }


    @GetMapping("/GymGoer/statistics")
    public String getPlayerStatistics(@RequestParam("gymGoerId") int gymGoerId, @RequestParam("machineId") int machineId ,Model model) {
        logger.info("Opening the statistics for exercise for user");


        Machine machine = machineService.findMachineById(machineId);
        if (machine == null) {
            logger.error("Machine not found with ID {}", machineId);
        }

        List<ExerciseSet> exerciseSets = exerciseSetService.getProgressForSpecificUser(gymGoerId, machineId);
        if (exerciseSets.size() == 0) {
            logger.error("No exercise sets found for user");
        }


        Integer sessionId = null;

        //the data for the charts
        List<Map<String, Object>> statistics = new ArrayList<>();
        List<Map<String, Object>> volumeData = new ArrayList<>();

        //to use the month and the day of the session
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");

        for (ExerciseSet exerciseSet : exerciseSets) {

            //calling the session so it can display the date
            Session session = exerciseSet.getExercise().getSession();
            sessionId = session.getSession_id();

            //formatting the date for the end screen to check from the session and then return the month and day
            String formattedDate = (session != null && session.getStartSession() != null) ? session.getStartSession().format(formatter) : "Null";

            Map<String, Object> statisticsDataChart = new HashMap<>();
            statisticsDataChart.put("weightCount", exerciseSet.getWeightCount());
            statisticsDataChart.put("date", formattedDate);
            //calculating the total repetition
            statisticsDataChart.put("repCount", exerciseSet.getRepetitionCount());
            statisticsDataChart.put("volumeDataStats", exerciseSet.getWeightCount() * exerciseSet.getRepetitionCount());

            //adding it to the list
            statistics.add(statisticsDataChart);


            // the data for the volume
            Map<String, Object> volumeDataChart = new HashMap<>();
            volumeDataChart.put("volumeD", exerciseSet.getWeightCount() * exerciseSet.getRepetitionCount());
            volumeDataChart.put("date", formattedDate);

            //adding it to the map
            volumeData.add(volumeDataChart);
        }

        //adding the statistics to the data



        logger.info("Statistics: {}", statistics);
        logger.info("Volume Data: {}", volumeData);
        logger.info("Machine Name: {}", machine.getName());
        //adding the attributes to the model
        model.addAttribute("exerciseSets", exerciseSets);
        model.addAttribute("statistics", statistics);
        model.addAttribute("volumeData", volumeData);
        model.addAttribute("machineName", machine.getName());
        model.addAttribute("machineId",machineId);
        model.addAttribute("userId",gymGoerId);
        model.addAttribute("sessionId", sessionId);

        return "GymGoer/statistics";
    }

    @GetMapping("/getChartData/{chartType}")
    @ResponseBody
    public List<Map<String, Object>> getChartData(@PathVariable String chartType, @RequestParam("gymGoerId") int gymGoerId, @RequestParam("machineId") int machineId) {
//        logger.info("Choosing the data from which table should be displayed");

//        logger.info("Fetching chart data for gymGoerId: " + gymGoerId + " and machineId: " + machineId);
//        logger.info("Chart Type: {}, gymGoerId: {}, machineId: {}", chartType, gymGoerId, machineId);
//        if (!List.of("reps", "weight", "volume").contains(chartType)) {
//            throw new IllegalArgumentException("Invalid chartType");
//        }

        return switch (chartType) {
            case "weights" ->  exerciseSetService.getWeightData(gymGoerId,machineId);
            case "volume" ->  exerciseSetService.getVolumeData(gymGoerId,machineId);
            case "reps" -> exerciseSetService.getRepetitionData(gymGoerId,machineId);
            default -> throw new IllegalArgumentException("Invalid chartType");
        };
    }

    @GetMapping("/gymhome/{username}/machines/{gymID}/machineReview")
    public String viewReviewMachine(@PathVariable String username, @PathVariable int gymID, @RequestParam("idMachine") Integer idMachine, Model model) {
        UserCredentials user = userService.getUserCredentialsByUsername(username);
        model.addAttribute("LocalDate", LocalDate.now());
        model.addAttribute("user", user);
        model.addAttribute("gym", gymService.findGymById(gymID));
        Machine machine = machineService.findMachineById(idMachine);
        logger.debug("View Machine: " + machine);
        model.addAttribute("machine", machine);

        // Retrieve the date of the Last maintained
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String lastMaintained = "No Information";
        if (machine.getLastTimeChecked() != null) {
             lastMaintained =  machine.getLastTimeChecked().format(format);
        }
        model.addAttribute("lastMainteinedDate", lastMaintained);
        logger.info("lastMainteinedDate: {}", lastMaintained);

        // Get the data for Machine Usage
        HashMap<String, Integer> machineUsage = new HashMap<>();
        if (machineService.findUsageOfMachineByIdPerDay(idMachine) != null) {
            machineUsage = machineService.findUsageOfMachineByIdPerDay(idMachine);
            logger.info("Machine usage data: {}", machineUsage);
        }
        model.addAttribute("machineUsage", machineUsage);
        List<String> machineUsageKeys = new ArrayList<>(machineUsage.keySet());
        List<Integer> machineUsageValues = new ArrayList<>(machineUsage.values());

        model.addAttribute("machineUsageKeys", machineUsageKeys);
        model.addAttribute("machineUsageValues", machineUsageValues);

        return "GymOwner/machine_review";
    }



}
