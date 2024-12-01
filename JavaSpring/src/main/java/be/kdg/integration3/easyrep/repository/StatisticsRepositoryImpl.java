package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.model.sessions.PlayerStatisticsDTO;
import be.kdg.integration3.easyrep.model.sessions.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StatisticsRepositoryImpl implements StatisticsRepository {
    private final Logger logger = LoggerFactory.getLogger(StatisticsRepositoryImpl.class);

    public static List<ExerciseSet> machineSets = new ArrayList<ExerciseSet>();

//    @PersistenceContext
//    private EntityManager entityManager;

    public static List<ExerciseSet> createDummyData() {
        List<ExerciseSet> machineSets = new ArrayList<>();

        // Add dummy data
//        machineSets.add(new ExerciseSet(12, 45.0,
//                LocalDateTime.of(2024, 12, 1, 9, 0),
//                LocalDateTime.of(2024, 12, 1, 9, 5)));
//        machineSets.add(new ExerciseSet(10, 50.0,
//                LocalDateTime.of(2024, 12, 3, 9, 10),
//                LocalDateTime.of(2024, 12, 3, 9, 15)));
//        machineSets.add(new ExerciseSet(15, 40.0,
//                LocalDateTime.of(2024, 12, 2, 9, 20),
//                LocalDateTime.of(2024, 12, 2, 9, 30)));
//        machineSets.add(new ExerciseSet(8, 60.0,
//                LocalDateTime.of(2024, 12, 4, 10, 0),
//                LocalDateTime.of(2024, 12, 4, 10, 10)));
//        machineSets.add(new ExerciseSet(20, 55.0,
//                LocalDateTime.of(2024, 12, 5, 11, 30),
//                LocalDateTime.of(2024, 12, 5, 11, 40)));


        return machineSets;
    }

    @Override
    public List<ExerciseSet> getPlayerStatistics() {


//        String jpql ="""
//                select new  be.kdg.integration3.easyrep.model.sessions.PlayerStatisticsDTO(g.username, count(s.session_ID), sum(r.weight))
//                from gym_goer g
//                join session s ON g.user_id = s.user_id
//                join set st ON s.session_id = st.session_id
//                join routine r ON st.set_id = r.set_id
//                group by g.user_id
//                """;
//        TypedQuery<PlayerStatisticsDTO> query = entityManager.createQuery(jpql, PlayerStatisticsDTO.class);
//        return query.getResultList();
        return machineSets ;
    }
}
