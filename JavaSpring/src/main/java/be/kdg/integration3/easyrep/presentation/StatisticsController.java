//package be.kdg.integration3.easyrep.presentation;
//
//import be.kdg.integration3.easyrep.model.sessions.PlayerStatisticsDTO;
//import be.kdg.integration3.easyrep.model.sessions.RoutineSession;
//import be.kdg.integration3.easyrep.service.StatisticsService;
//import be.kdg.integration3.easyrep.service.StatisticsServiceImpl;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
//@Controller
//@RequestMapping("/workout")
//public class StatisticsController {
//    private final Logger logger = LoggerFactory.getLogger(StatisticsController.class);
//
//    private final StatisticsService statisticsService;
//
//    @Autowired
//    public StatisticsController(StatisticsService statisticsService) {
//        this.statisticsService = statisticsService;
//    }
//
//    @GetMapping("/statistics")
//        public String getPlayerStatistics(Model model) {
//        List<PlayerStatisticsDTO> statistics = statisticsService.getPlayerStatistics();
//        model.addAttribute("statistics", statistics);
//            return "/GymGoer/statistics";
//        }
//
//}
