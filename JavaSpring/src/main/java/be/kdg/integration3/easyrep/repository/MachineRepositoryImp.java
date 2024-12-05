//package be.kdg.integration3.easyrep.repository;
//
//import be.kdg.integration3.easyrep.model.Machine;
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
//public class MachineRepositoryImp {
//    private Logger logger = LoggerFactory.getLogger(MachineRepositoryImp.class);
//
//    @PersistenceContext
//    private EntityManager em;
//
//    public List<Machine> findAll() {
//        logger.info("Find all machines");
//        return em.createQuery("select m from Machine m", Machine.class).getResultList();
//    }
//    public Machine findById(int id) {
//        logger.info("Find Machine by id: " + id);
//        return em.find(Machine.class, id);
//    }
//
//    public Machine createMachine(Machine machine){
//        logger.info("Create machine: " + machine);
//        em.persist(machine);
//        return machine;
//    }
//
//    @Transactional
//    public void delete (Machine machine){
//        logger.info("Delete machine: " + machine);
//        em.remove(machine);
//    }
//    @Transactional
//    public void update (Machine machine){
//        em.merge(machine);
//    }
//
//    public Machine findByName(String name) {
//        return em.find(Machine.class, name);
//    }
//
//}
