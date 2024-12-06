//package be.kdg.integration3.easyrep.repository;
//
//import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.FluentQuery;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//@Repository
//public class ExerciseSetRepositoryImpl implements ExerciseSetRepository {
//
//    private final Logger logger = LoggerFactory.getLogger(ExerciseSetRepositoryImpl.class);
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public List<ExerciseSet> findAllExerciseSet() {
//        return List.of();
//    }
//
//    @Override
//    public ExerciseSet findExerciseSetById(int id) {
//        return null;
//    }
//
////    @Override
////    public ExerciseSet createExerciseSet(ExerciseSet exerciseSet) {
////        return null;
////    }
////
////    @Override
////    public void updateSet(ExerciseSet exerciseSet) {
////
////    }
//
//    @Override
//    public void delete(ExerciseSet exerciseSet) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends Integer> integers) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends ExerciseSet> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public void flush() {
//
//    }
//
//    @Override
//    public <S extends ExerciseSet> S saveAndFlush(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends ExerciseSet> List<S> saveAllAndFlush(Iterable<S> entities) {
//        return List.of();
//    }
//
//    @Override
//    public void deleteAllInBatch(Iterable<ExerciseSet> entities) {
//
//    }
//
//    @Override
//    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
//
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//
//    }
//
//    @Override
//    public ExerciseSet getOne(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public ExerciseSet getById(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public ExerciseSet getReferenceById(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public <S extends ExerciseSet> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends ExerciseSet> List<S> findAll(Example<S> example) {
//        return List.of();
//    }
//
//    @Override
//    public <S extends ExerciseSet> List<S> findAll(Example<S> example, Sort sort) {
//        return List.of();
//    }
//
//    @Override
//    public <S extends ExerciseSet> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends ExerciseSet> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends ExerciseSet> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    public <S extends ExerciseSet, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//        return null;
//    }
//
//    @Override
//    public <S extends ExerciseSet> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends ExerciseSet> List<S> saveAll(Iterable<S> entities) {
//        return List.of();
//    }
//
//    @Override
//    public Optional<ExerciseSet> findById(Integer integer) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Integer integer) {
//        return false;
//    }
//
//    @Override
//    public List<ExerciseSet> findAll() {
//        return List.of();
//    }
//
//    @Override
//    public List<ExerciseSet> findAllById(Iterable<Integer> integers) {
//        return List.of();
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Integer integer) {
//
//    }
//
//    @Override
//    public List<ExerciseSet> findAll(Sort sort) {
//        return List.of();
//    }
//
//    @Override
//    public Page<ExerciseSet> findAll(Pageable pageable) {
//        return null;
//    }
//
////    @Override
////    public List<ExerciseSet> findAllExerciseSet(){
////        logger.info("Find all exercise sets");
////        return  em.createQuery("select e from ExerciseSet e", ExerciseSet.class).getResultList();
////    }
////
////    @Override
////    public ExerciseSet findExerciseSetById(int id) {
////        logger.info("Find exercise set by id: {}", id);
////        return em.find(ExerciseSet.class, id);
////    }
////
////    @Transactional
////    @Override
////    public ExerciseSet createExerciseSet(ExerciseSet exerciseSet) {
////        logger.info("Create exercise set: {}", exerciseSet);
////        em.persist(exerciseSet);
////        return exerciseSet;
////    }
////
////    @Transactional
////    @Override
////    public void updateSet(ExerciseSet exerciseSet) {
////        logger.info("Update exercise set");
////        em.merge(exerciseSet);
////
////    }
////
////    @Transactional
////    @Override
////    public void delete(ExerciseSet exerciseSet) {
////        logger.info("Delete exercise set: {}", exerciseSet);
////        if (!em.contains(exerciseSet)) {
////            System.out.println("DETACHED EXERCISE SET");
////            exerciseSet = em.merge(exerciseSet);
////        }
////        em.remove(exerciseSet);
////    }
//
//
//}
