package be.kdg.integration3.easyrep.service;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsService {

    void addRoutineSession(LocalDateTime startSession, LocalDateTime endSession,String status);

    List<PlayerStatisticsDTO> getPlayerStatistics();
}
