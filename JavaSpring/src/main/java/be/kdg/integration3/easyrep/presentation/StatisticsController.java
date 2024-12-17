package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.GymGoer;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
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


    @GetMapping("/{username}/statistics")
    public String getPlayerStatistics(@PathVariable String username, @RequestParam("machineId") int machineId ,Model model) {
        logger.info("Opening the statistics for exercise for user");

        Machine machine = machineService.findMachineById(machineId);
        if (machine == null) {
            logger.error("Machine not found with ID {}", machineId);
        }

        UserCredentials userCredentials = userService.getUserCredentialsByUsername(username);
        GymGoer gymGoer = userService.getGymGoerByUserId(userCredentials.getUserId());

        List<ExerciseSet> exerciseSets = exerciseSetService.getProgressForSpecificUser(gymGoer.getUserId(), machineId);
        if (exerciseSets.size() == 0) {
            logger.error("No exercise sets found for user");
        }


        Integer sessionId = null;

        //the data for the charts
        List<Map<String, Object>> statistics = new ArrayList<>();
        List<Map<String, Object>> volumeData = new ArrayList<>();

        //to use the month and the day of the session
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");

        //tracks set number for each date
        Map<String, Integer> dataSetsCount = new HashMap<>();

        for (ExerciseSet exerciseSet : exerciseSets) {

            //calling the session so it can display the date
            Session session = exerciseSet.getExercise().getSession();
            sessionId = session.getSession_id();

            //formatting the date for the end screen to check from the session and then return the month and day
            String formattedDate = (session != null && session.getStartSession() != null) ? session.getStartSession().format(formatter) : "Null";
            //increasing the number of sets for this specific date
            int currentSetNumber = dataSetsCount.getOrDefault(formattedDate, 0) + 1;
            dataSetsCount.put(formattedDate, currentSetNumber);

            //calculating the volume
            double volume = exerciseSet.getWeightCount() *exerciseSet.getRepetitionCount();
            //rounding the data until 2 decimals
            BigDecimal roundVolume = new BigDecimal(volume).setScale(2, RoundingMode.HALF_UP);

            Map<String, Object> statisticsDataChart = new HashMap<>();

            statisticsDataChart.put("weightCount", exerciseSet.getWeightCount());
            statisticsDataChart.put("date", formattedDate);
            statisticsDataChart.put("repCount", exerciseSet.getRepetitionCount());
            statisticsDataChart.put("volumeDataStats", roundVolume.doubleValue());
            statisticsDataChart.put("setNumber", currentSetNumber);

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
        model.addAttribute("userId",gymGoer.getUserId());
        model.addAttribute("userName",gymGoer.getUserCredentials().getUsername());
        model.addAttribute("sessionId", sessionId);

        return "GymGoer/statistics";
    }

    @GetMapping("/getChartData/{chartType}")
    @ResponseBody
    public List<Map<String, Object>> getChartData(@PathVariable String chartType, @RequestParam("gymGoerId") int gymGoerId, @RequestParam("machineId") int machineId) {

        return switch (chartType) {
            case "weights" ->  exerciseSetService.getWeightData(gymGoerId,machineId);
            case "volume" ->  exerciseSetService.getVolumeData(gymGoerId,machineId);
            case "reps" -> exerciseSetService.getRepetitionData(gymGoerId,machineId);
            default -> throw new IllegalArgumentException("Invalid chartType");
        };
    }

    @GetMapping("/pastSession/{username}/statistics")
    public String getPastSessionExercise(@PathVariable String username, @RequestParam("machineId") int machineId, @RequestParam("sessionId") int sessionId ,Model model) {
        logger.info("Opening the statistics for exercise for user");

        Machine machine = machineService.findMachineById(machineId);
        if (machine == null) {
            logger.error("Machine not found with ID {}", machineId);
        }

        UserCredentials userCredentials = userService.getUserCredentialsByUsername(username);
        GymGoer gymGoer = userService.getGymGoerByUserId(userCredentials.getUserId());

        List<ExerciseSet> exerciseSets = exerciseSetService.getProgressForSpecificUser(gymGoer.getUserId(), machineId);
        if (exerciseSets.size() == 0) {
            logger.error("No exercise sets found for user");
        }


//        Integer sessionId = null;
//        Integer sessionNumber = null;

        //the data for the charts
        List<Map<String, Object>> statistics = new ArrayList<>();

        //to use the month and the day of the session
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");

        //tracks set number for each date
        Map<String, Integer> dataSetsCount = new HashMap<>();

        for (ExerciseSet exerciseSet : exerciseSets) {

            //calling the session so it can display the date
            Session session = exerciseSet.getExercise().getSession();
//            sessionId = session.getSession_id();

            //formatting the date for the end screen to check from the session and then return the month and day
            String formattedDate = (session != null && session.getStartSession() != null) ? session.getStartSession().format(formatter) : "Null";
            //increasing the number of sets for this specific date
            int currentSetNumber = dataSetsCount.getOrDefault(formattedDate, 0) + 1;
            dataSetsCount.put(formattedDate, currentSetNumber);

            //calculating the volume
            double volume = exerciseSet.getWeightCount() *exerciseSet.getRepetitionCount();
            //rounding the data until 2 decimals
            BigDecimal roundVolume = new BigDecimal(volume).setScale(2, RoundingMode.HALF_UP);

            Map<String, Object> statisticsDataChart = new HashMap<>();

            statisticsDataChart.put("weightCount", exerciseSet.getWeightCount());
            statisticsDataChart.put("date", formattedDate);
            statisticsDataChart.put("repCount", exerciseSet.getRepetitionCount());
            statisticsDataChart.put("volumeDataStats", roundVolume.doubleValue());
            statisticsDataChart.put("setNumber", currentSetNumber);

            //adding it to the list
            statistics.add(statisticsDataChart);


        }

        //adding the statistics to the data
        logger.info("Statistics: {}", statistics);
        logger.info("Machine Name: {}", machine.getName());
        //adding the attributes to the model
        model.addAttribute("exerciseSets", exerciseSets);
        model.addAttribute("statistics", statistics);
        model.addAttribute("machineName", machine.getName());
        model.addAttribute("machineId",machineId);
        model.addAttribute("userId",gymGoer.getUserId());
        model.addAttribute("userName",gymGoer.getUserCredentials().getUsername());
        model.addAttribute("sessionId", sessionId);

        return "GymGoer/specific_past_statistics";
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
        HashMap<Integer, Integer> machineUsage = new HashMap<>();
        if (machineService.findUsageOfMachineByIdPerDay(idMachine) != null) {
            machineUsage = machineService.findUsageOfMachineByIdPerDay(idMachine);
            logger.info("Machine usage data: {}", machineUsage);
        }
        model.addAttribute("machineUsage", machineUsage);
        List<Integer> machineUsageKeys = new ArrayList<>(machineUsage.keySet());
        List<Integer> machineUsageValues = new ArrayList<>(machineUsage.values());

        model.addAttribute("machineUsageKeys", machineUsageKeys);
        model.addAttribute("machineUsageValues", machineUsageValues);

        return "GymOwner/machine_review";
    }



}
