package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.service.ExerciseSetService;
import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.service.MachineService;
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



    @Autowired
    public StatisticsController(ExerciseSetService exerciseSetService, MachineService machineService) {
        this.machineService = machineService;
        this.exerciseSetService = exerciseSetService;
    }


    @GetMapping("/GymGoer/statistics")
    public String getPlayerStatistics(@RequestParam("gymGoerId") int gymGoerId, @RequestParam("machineId") int machineId ,Model model) {
        logger.info("Opening the statistics for exercise for user");

        //getting the attributes from the query in the repository and then the service
        List<ExerciseSet> exerciseSets = exerciseSetService.getProgressForSpecificUser(gymGoerId, machineId);

        //the data for the charts
        List<Map<String, Object>> statistics = new ArrayList<>();
        List<Map<String, Object>> volumeData = new ArrayList<>();

        for (ExerciseSet exerciseSet : exerciseSets) {
            Map<String, Object> statisticsDataChart = new HashMap<>();
            statisticsDataChart.put("weightCount", exerciseSet.getWeightCount());
            statisticsDataChart.put("date", exerciseSet.getStartTime());
            //calculating the total repetition
            statisticsDataChart.put("repCount", exerciseSet.getRepetitionId().size());
            //adding it to the list
            statistics.add(statisticsDataChart);


            // the data for the volume
            Map<String, Object> volumeDataChart = new HashMap<>();
            volumeDataChart.put("volumeD", exerciseSet.getWeightCount() * exerciseSet.getRepetitionId().size());
            volumeDataChart.put("date", exerciseSet.getStartTime());
            //adding it to the map
            volumeData.add(volumeDataChart);
        }

        //adding the attributes to the model
        model.addAttribute("exerciseSets", exerciseSets);
        model.addAttribute("statistics", statistics);
        model.addAttribute("volumeData", volumeData);

        return "GymGoer/statistics";
    }

    @GetMapping("/getChartData/{chartType}")
    @ResponseBody
    public List<Map<String, Object>> getChartData(@PathVariable String chartType) {
        List<Map<String, Object>> chartData = new ArrayList<>();

        return chartData;
    }


    @PostMapping("/statisticsClose")
    public String exitPlayerStatistics() {
        logger.info("Exiting the page");
        return "GymGoer/end_screen_session";
    }

    @GetMapping("/GymOwner/machines/machine_review")
    public String getMachineReview(@RequestParam("idMachine") int idMachine, Model model) {
        logger.info("Get Mapping to a machine review");
        model.addAttribute("LocalDate", LocalDate.now());
        String lastMaintained = "12/08/2021"; // Example value, can be dynamic
        model.addAttribute("lastMainteinedDate", lastMaintained);
        logger.info("lastMainteinedDate: {}", lastMaintained);
        Machine machine = machineService.findMachineById(idMachine);
        logger.debug("View Machine: " + machine);
        model.addAttribute("machine", machine);

        return "GymOwner/machine_review";
    }



}
