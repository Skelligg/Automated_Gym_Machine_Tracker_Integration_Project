package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.sessions.PlayerStatisticsDTO;
import be.kdg.integration3.easyrep.service.MachineService;
import be.kdg.integration3.easyrep.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class StatisticsController {
    private final Logger logger = LoggerFactory.getLogger(StatisticsController.class);
    private MachineService machineService;
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService, MachineService machineService) {
        this.statisticsService = statisticsService;
        this.machineService = machineService;
    }

    @GetMapping("/GymGoer/statistics")
    public String getPlayerStatistics(Model model) {
        logger.info("Mapping the statistics from the gym");
        List<PlayerStatisticsDTO> statistics = statisticsService.getPlayerStatistics();
        logger.info("Gym goer statistics: {}", statistics);

        model.addAttribute("statistics", statistics);
        model.addAttribute("LocalDate", LocalDate.now());
        model.addAttribute("volume", "10");

        return "GymGoer/statistics";
    }
    @PostMapping("/GymGoer/statistics")
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
