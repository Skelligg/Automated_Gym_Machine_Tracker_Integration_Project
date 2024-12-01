package be.kdg.integration3.easyrep.service.session;

import be.kdg.integration3.easyrep.model.sessions.Session;

import java.util.List;

public interface SessionService {

    Session createSession(Session session);

    List<Session> getAllSessions();

    void emptyRoutines();

    Session getSessionById(int id);

}
