package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Arduino;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class ArduinoRepositoryImpl {

    // this would retrieve from the database the MACHINE_ID and the ipaddress from the machine/arduino
//    private static Map<String, String> arduinoInfo = new HashMap<>(Map.of("row", ""));

    @PersistenceContext
    private EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(ArduinoRepositoryImpl.class.getName());
//    private ArduinoRepositoryImpl() {
//        createArduinoInfo("Test-ID", "127.0.0.1");
//        createArduinoInfo("Test-ID2", "127.0.0.2");
//    }

//    public void createArduinoInfo(String id, String ipAddress) {
//        arduinoInfo.put(id, ipAddress);
//    }

    // Getter for IP Address by ID // dont know about this one
    public String getIpAddress(String arduinoId) {
        logger.info("getting the IP address");
       try {
           String ipAddress = em.createQuery("select a.ipAddress from Arduino a where a.arduinoId = :arduinoId",String.class).setParameter("arduinoId", arduinoId).getSingleResult();
       logger.info("the IP address is {}", ipAddress);
       return ipAddress;
       }catch (Exception e) {
           logger.error("error occured while getting the IP address");
           return null;
       }
    }
    public List<Arduino> findAll() {
        logger.info("finding all the Arduino");
        return em.createQuery("select a from Arduino a", Arduino.class).getResultList();
    }
    public Arduino findById(int arduinoId) {
        logger.info("finding the Arduino");
        return em.find(Arduino.class, arduinoId);
    }

    @Transactional
    public Arduino createArduino(Arduino arduino) {
       em.persist(arduino);
       return arduino;
    }
    @Transactional
    public void updateArduino(Arduino arduino) {
        em.merge(arduino);
    }
    @Transactional
    public void deleteArduino(int arduinoId) {
        Arduino arduino = em.find(Arduino.class, arduinoId);
        em.remove(arduino);
    }
//    public void setIpAddress(String id, String ipAddress) {
////        get id and info from database and check if the id exists in the database
//        arduinoInfo.replace(id, ipAddress);
//
//    }

    // Getter for all IDs // DO NOT UNDERSTAND THESE CLASSES VERY WELL< PROBABLY WILL CHANGE
//    public Set<String> findAllSets() {
//        logger.info("Fetching all ExerciseSet IDs.");
//        TypedQuery<Integer> query = em.createQuery(
//                "SELECT s.setId FROM ExerciseSet s", Integer.class);
//
//        List<Integer> results = query.getResultList();
////        return Set.copyOf(results);
//        return null;
//    }
//
//    // Getter for all IP Addresses
//    public Set<String> getAllIpAddresses() {
//        logger.info("getting the IP addresses");
//        TypedQuery<String> query = em.createQuery(
//                "SELECT a.ipAddress FROM Arduino a", String.class);
//
//        List<String> results = query.getResultList();
//        return Set.copyOf(results);
//    }
//
//
//    public void getArduinoInfo() {
////        return em.createQuery("select a from Arduino a",Arduino.class).getResultList();
//        return null;
//    }
//
}
