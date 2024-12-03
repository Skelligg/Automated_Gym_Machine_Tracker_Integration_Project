package be.kdg.integration3.easyrep.repository.session;

import be.kdg.integration3.easyrep.model.sessions.Session;
import jakarta.transaction.Transactional;

import java.util.List;

public interface SessionRepository {
    @Transactional
    void saveSession(Session session);

    @Transactional
    void deleteSession(Session session);

    Session getSessionById(int sessionId);

    List<Session> getAllSessions();
}
