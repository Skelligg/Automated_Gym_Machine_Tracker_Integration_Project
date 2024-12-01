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
    public String getPlayerStatistics(Model model) {
        logger.info("Mapping the statistics from the gym");
        List<ExerciseSet> statistics = exerciseSetService.getAllExerciseSets();

        logger.info("Gym goer statistics: {}", statistics);
        List<Map<String, Object>> volumeData = new ArrayList<>();
        for (ExerciseSet exerciseSet : statistics) {
            Map<String, Object> volumeMap = new HashMap<>();
            volumeMap.put("volume", exerciseSet.getRepCount() * exerciseSet.getWeightCount());
            volumeMap.put("dateTime", exerciseSet.getEndTime().format(DateTimeFormatter.ofPattern("MM/dd")));
            volumeData.add(volumeMap);
        }

        model.addAttribute("statistics", statistics);
        model.addAttribute("volumeData", volumeData);
        model.addAttribute("LocalDate", LocalDate.now());
        model.addAttribute("volume", "10");

        return "GymGoer/statistics";
    }

    @GetMapping("/getChartData/{chartType}")
    @ResponseBody
    public List<Map<String, Object>> getChartData(@PathVariable String chartType) {
        List<Map<String, Object>> chartData = new ArrayList<>();

        for (ExerciseSet set : exerciseSetService.getAllExerciseSets()) {
            Map<String, Object> dataPoint = new HashMap<>();

            dataPoint.put("dateTime", set.getEndTime().format(DateTimeFormatter.ofPattern("MM/dd")));
            switch (chartType){
                case "weights": dataPoint.put("value",set.getWeightCount()); break;
                case "volume" : double volume = set.getWeightCount() * set.getRepCount();
                dataPoint.put("value", volume); break;
                case "reps":dataPoint.put("value", set.getRepCount()); break;
                default: throw new IllegalArgumentException("Invalid chart " + chartType);
            }
            chartData.add(dataPoint);
        }
        return chartData;
    }

    @PostMapping("/GymGoer/statisticsClose")
    public String exitPlayerStatistics() {
        logger.info("Exiting the page");
        return "redirect:/";
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
