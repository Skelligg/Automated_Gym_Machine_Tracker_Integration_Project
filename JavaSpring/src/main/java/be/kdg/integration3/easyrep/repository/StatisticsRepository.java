package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import be.kdg.integration3.easyrep.model.sessions.PlayerStatisticsDTO;

import java.util.List;

public interface StatisticsRepository {

    List<MachineSet> getPlayerStatistics();



}
