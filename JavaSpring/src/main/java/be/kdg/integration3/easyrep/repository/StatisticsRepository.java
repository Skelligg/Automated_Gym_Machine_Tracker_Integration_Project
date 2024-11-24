package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.PlayerStatisticsDTO;
import be.kdg.integration3.easyrep.model.sessions.RoutineSession;

import java.util.List;

public interface StatisticsRepository {

    List<PlayerStatisticsDTO> getPlayerStatistics();



}
