package be.kdg.integration3.easyrep.service.session;

import be.kdg.integration3.easyrep.model.sessions.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionService {

    Session createSession(Session session);

    List<Session> getAllSessions();

    Session getSessionById(int id);

    Session getActiveSessionByMachineId(int machineId);

    int getSessionCountByUserId(int userId);
    String getTimeForSessionById(int sessionId);

    Session findSessionByStartAndEndTime(LocalDateTime startTime, LocalDateTime endTime);
    List<Session> getAllSessionsFromUser(String username);

    Session findSessionByStartAndEndTimeAndUser(LocalDateTime startTime, LocalDateTime endTime, int userId);
}
