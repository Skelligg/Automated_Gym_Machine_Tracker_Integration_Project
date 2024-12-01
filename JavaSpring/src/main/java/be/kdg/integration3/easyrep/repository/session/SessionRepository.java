package be.kdg.integration3.easyrep.repository.session;

import be.kdg.integration3.easyrep.model.sessions.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SessionRepository {

    private Logger logger = LoggerFactory.getLogger(SessionRepository.class);
    private static List<Session> sessions = new ArrayList<Session>();


    public Session createSession(Session session) {
        sessions.add(session);
        return session;
    }

    public List<Session> getAllSessions() {
        return sessions;
    }

    public void emptySessions() {
        sessions.clear();
    }

    public Session getSessionById(int id) {
        for (Session session : sessions) {
            if (session.getId() == id) {
                return session;
            }
        }
        return null;
    }
}
