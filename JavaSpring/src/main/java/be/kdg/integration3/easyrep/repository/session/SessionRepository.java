package be.kdg.integration3.easyrep.repository.session;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.sessions.Session;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    Session findById(int id);

    Optional<Session> findByStartSessionAndEndSession(LocalDateTime startSession, LocalDateTime endSession);

    //to count the number of finished sessions for a specific user
    @Query("select count(s) from Session s where s.gymGoerId.userId = :userId")
    int countSessionByUserId(@Param("userId")int userId);

    //how long was the specific session
    @Query("select TIMESTAMPDIFF(SECOND,s.startSession, s.endSession) from Session s where s.session_id = :sessionId")
    Integer getSessionDurationInSeconds(@Param("sessionId") int sessionId);

    @Query("SELECT s FROM Session s WHERE s.gymGoerId.userId = :userId")
    List<Session> findAllSessionsFromUser(int userId);

    @Query("SELECT s FROM Session s WHERE s.gymGoerId.userId = :gymGoerId")
    List<Session> findAllByGymGoerId(@Param("gymGoerId") int gymGoerId);
}
