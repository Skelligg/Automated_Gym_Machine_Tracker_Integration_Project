package be.kdg.integration3.easyrep.repository.session;

import be.kdg.integration3.easyrep.model.GymGoer;
import be.kdg.integration3.easyrep.model.sessions.Session;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    Session findById(int id);


    //to count the number of finished sessions for a specific user
    @Query("select count(s) from Session s where lower(s.status) = 'finish' and s.gymGoerId.userId = :userId")
    int countSessionByUserId(@Param("userId")int userId);

    //how long was the specific session
    @Query("select TIMESTAMPDIFF(SECOND,s.startSession, s.endSession) from Session s where s.session_id = :sessionId")
    Integer getSessionDurationInSeconds(@Param("sessionId") int sessionId);
}
