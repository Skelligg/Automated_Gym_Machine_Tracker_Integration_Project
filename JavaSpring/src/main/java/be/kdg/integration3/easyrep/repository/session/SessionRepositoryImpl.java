//package be.kdg.integration3.easyrep.repository.session;
//
//import be.kdg.integration3.easyrep.model.sessions.Session;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class SessionRepositoryImpl implements SessionRepository {
//
//    private Logger logger = LoggerFactory.getLogger(SessionRepositoryImpl.class);
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Transactional
//    @Override
//    public void saveSession(Session session) {
//        logger.info("Saving session");
//        em.persist(session);
//    }
//
//
//    @Transactional
//    @Override
//    public void deleteSession(Session session) {
//        logger.info("Deleting session with ID: {}", session.getSession_id());
//
//        Session managedSession = em.find(Session.class, session.getSession_id()); // Retrieve the managed entity
//        if (managedSession != null) {
//            em.remove(managedSession); // Remove the managed session
//        } else {
//            throw new IllegalArgumentException("Session with ID " + session.getSession_id() + " not found, cannot delete.");
//        }
//    }
//
//
//    @Override
//    public Session getSessionById(int sessionId) {
//        logger.info("Retrieving session");
//        return em.find(Session.class, sessionId);
//    }
//
//    @Override
//    public List<Session> getAllSessions() {
//        logger.info("Retrieving all sessions");
//        String jpql = "SELECT s from Session s";
//        return em.createQuery(jpql, Session.class).getResultList();
//    }
//
//}
