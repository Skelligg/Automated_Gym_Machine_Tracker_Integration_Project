package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;

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

    public static List<ExerciseSet> exerciseSets = new ArrayList<ExerciseSet>();

//    @PersistenceContext
//    private EntityManager entityManager;

    public static List<ExerciseSet> createDummyData() {
        List<ExerciseSet> exerciseSets = new ArrayList<>();

        //adding the dummy data
        exerciseSets.add(new ExerciseSet(45.0, LocalDateTime.of(2024, 12, 1, 9, 0),
                LocalDateTime.of(2024, 12, 1, 9, 10),12));
        exerciseSets.add(new ExerciseSet(55.0, LocalDateTime.of(2024, 12, 2, 9, 0),
                LocalDateTime.of(2024, 12, 2, 9, 20),11));
        exerciseSets.add(new ExerciseSet(20.0, LocalDateTime.of(2024, 12, 3, 9, 0),
                LocalDateTime.of(2024, 12, 3, 9, 30),8));
        exerciseSets.add(new ExerciseSet(35.5, LocalDateTime.of(2024, 12, 4, 9, 0),
                LocalDateTime.of(2024, 12, 4, 9, 40),6));
        exerciseSets.add(new ExerciseSet(60.4, LocalDateTime.of(2024, 12, 5, 9, 0),
                LocalDateTime.of(2024, 12, 5, 9, 50),16));

        return exerciseSets;
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
        return exerciseSets ;
    }
}
