package be.kdg.integration3.easyrep.service.session;


import be.kdg.integration3.easyrep.model.Routine;
import be.kdg.integration3.easyrep.model.sessions.Session;
import be.kdg.integration3.easyrep.repository.session.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);
    SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session createSession(Session session) {
        sessionRepository.createSession(session);
        return session;
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.getAllSessions();
    }

    @Override
    public void emptyRoutines() {
        sessionRepository.emptySessions();
    }


    @Override
    public Session getSessionById(int id) {
        logger.info("in service getting session repo");
        return sessionRepository.getSessionById(id);
    }

}
