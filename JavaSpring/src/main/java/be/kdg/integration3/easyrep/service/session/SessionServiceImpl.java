package be.kdg.integration3.easyrep.service.session;


import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.Session;
import be.kdg.integration3.easyrep.repository.ExerciseRepository;
import be.kdg.integration3.easyrep.repository.MachineRepository;
import be.kdg.integration3.easyrep.repository.session.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    private final ExerciseRepository exerciseRepository;
    private final MachineRepository machineRepository;
    Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);
    SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository, ExerciseRepository exerciseRepository, MachineRepository machineRepository) {
        this.sessionRepository = sessionRepository;
        this.exerciseRepository = exerciseRepository;
        this.machineRepository = machineRepository;
    }

    @Override
    public Session createSession(Session session) {
        sessionRepository.save(session);
        return session;
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }


    @Override
    public Session getSessionById(int id) {
        logger.info("in service getting session repo");
        return sessionRepository.findById(id);
    }
    public int getSessionCountByUserId(int userId) {
        return sessionRepository.countSessionByUserId(userId);
    }

    @Override
    public String getTimeForSessionById(int sessionId) {
        //get the time in seconds
        Integer totalSeconds = sessionRepository.getSessionDurationInSeconds(sessionId);

        //check if time is not null and calculate the time in hours and minutes and seconds
        if (totalSeconds != null) {
            long hours = totalSeconds / 3600;
            long minutes = (totalSeconds %3600) / 60;
            long seconds = totalSeconds % 60;
            return String.format("%dh %dm %ds", hours, minutes, seconds);
        }

        return null ;
    }

    public Session getActiveSessionByMachineId(int machineId){
        logger.info("!!Machine ID = {}", machineId);
        Machine machine = machineRepository.findByMachineId(machineId);

        List<Exercise> exercises = exerciseRepository.findExercisesByMachine(machine);
        Exercise exercise = exercises.stream()
                .max(Comparator.comparingInt(ex -> ex.getSession().getSession_id()))
                .orElse(null);

        Session session = sessionRepository.findById(exercise.getSession().getSession_id());
        logger.info("!!Session ID = {}", session.getSession_id());
        return session;

    }

    public Session findSessionByStartAndEndTime(LocalDateTime startTime, LocalDateTime endTime) {
        return sessionRepository.findByStartSessionAndEndSession(startTime, endTime)
                .orElse(null);
    }


}

