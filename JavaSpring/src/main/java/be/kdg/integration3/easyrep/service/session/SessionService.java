package be.kdg.integration3.easyrep.service.session;

import be.kdg.integration3.easyrep.model.sessions.Session;

import java.util.List;

public interface SessionService {

    Session createSession(Session session);

    List<Session> getAllSessions();

    Session getSessionById(int id);

    public int getSessionCountByUserId(int userId);

}
