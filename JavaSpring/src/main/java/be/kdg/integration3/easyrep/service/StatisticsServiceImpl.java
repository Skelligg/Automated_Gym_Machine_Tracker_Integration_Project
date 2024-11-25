//package be.kdg.integration3.easyrep.service;
//
//import be.kdg.integration3.easyrep.model.sessions.PlayerStatisticsDTO;
//import be.kdg.integration3.easyrep.model.sessions.RoutineSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class StatisticsServiceImpl implements StatisticsService {
//
//    private StatisticsServiceImpl statisticsService;
//
//    @Autowired
//    public StatisticsServiceImpl(StatisticsServiceImpl statisticsService) {
//        this.statisticsService = statisticsService;
//    }
//
//    @Override
//    public void addRoutineSession(LocalDateTime startSession, LocalDateTime endSession, String status) {
//        RoutineSession rs = new RoutineSession(startSession,endSession,status);
//
//    }
//
//    @Override
//    public List<PlayerStatisticsDTO> getPlayerStatistics() {
//        return  statisticsService.getPlayerStatistics();
//    }
//}
