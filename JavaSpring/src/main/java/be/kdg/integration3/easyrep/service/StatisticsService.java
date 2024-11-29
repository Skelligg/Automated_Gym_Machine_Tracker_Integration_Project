package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.sessions.PlayerStatisticsDTO;
import be.kdg.integration3.easyrep.model.sessions.RoutineSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public interface StatisticsService {

    void addRoutineSession(LocalDateTime startSession, LocalDateTime endSession,String status);

    List<PlayerStatisticsDTO> getPlayerStatistics();
}
