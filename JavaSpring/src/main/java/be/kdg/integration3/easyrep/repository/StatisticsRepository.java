package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;


import java.util.List;

public interface StatisticsRepository {

    List<ExerciseSet> getPlayerStatistics();



}
