//package be.kdg.integration3.easyrep.repository;
//
//import be.kdg.integration3.easyrep.model.sessions.PlayerStatisticsDTO;
//import be.kdg.integration3.easyrep.model.sessions.RoutineSession;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class StatisticsRepositoryImpl implements StatisticsRepository {
//    private final Logger logger = LoggerFactory.getLogger(StatisticsRepositoryImpl.class);
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<PlayerStatisticsDTO> getPlayerStatistics() {
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
//    }
//}
