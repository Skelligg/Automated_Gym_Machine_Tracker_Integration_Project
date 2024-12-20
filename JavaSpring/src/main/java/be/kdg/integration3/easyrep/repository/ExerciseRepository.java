package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    @Override
    List<Exercise> findAll();

    Exercise findByexerciseName(String name);

    Exercise findByexerciseId(Integer id);

    List<Exercise> findExercisesByMachine(Machine machine);

    Optional<Exercise> findBySessionAndExerciseName(Session session, String exerciseName);

    //query to find the past exercises for user and specific machine
    @Query("select e from Exercise e where e.session.gymGoerId.userId = :gymGoerId and e.machine.machineId = :machineId")
    List<Exercise> findExerciseByUserAndMachine(@Param("gymGoerId") int gymGoerId, @Param("machineId") int machineId);

    @Query("SELECT count(e)+1 FROM Exercise e JOIN ExerciseSet es ON (e.exerciseId = es.exercise.exerciseId) WHERE e.machine.name like 'Iso-Lateral Row (Machine)' OR e.machine.name like 'Iso-Lateral Machine Row'")
    Integer findNextExerciseId();
    //    private static final Logger log = LoggerFactory.getLogger(ExerciseRepository.class);
//    private static List<Exercise> exercises = new ArrayList<Exercise>();
//
//    @PersistenceContext
//    private EntityManager em;
//
////    public ExerciseRepository() {
////        exercises.add(new Exercise("row machine"));
////        exercises.add(new Exercise("bench press"));
////        exercises.add(new Exercise("squat machine"));
////        exercises.add(new Exercise("unilateral jerk"));
////    }
//
//    @Transactional
//    public void createExercise(Exercise exercise){
//        em.persist(exercise);
//    }
//
//    @Transactional
//    public void removeExercise(Exercise exercise) {
//        Exercise managedExercise = em.find(Exercise.class, exercise.getExerciseId()); // Ensure it's managed
//        if (managedExercise != null) {
//            em.remove(managedExercise); // Remove the managed entity
//        } else {
//            throw new IllegalArgumentException("Exercise with ID " + exercise.getExerciseId() + " not found, cannot remove.");
//        }
//    }

//    public List<Exercise> getExercises() {
//        return exercises;
//    }
//
//    public void emptyExercises() {
//        exercises.clear();
//    }
//
//    public List<Exercise> findByNameIn(List<String> names) {
//        log.info("trying to find the machines {}", names);
//        List<Exercise> findingExercises = new ArrayList<>();
//        for (Exercise exercise : exercises) {
//            if (names.contains(exercise.getName())) {
//                findingExercises.add(exercise);
//            }
//        }
//        return findingExercises;
//    }

}

