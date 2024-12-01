//package be.kdg.integration3.easyrep.repository.routines;
//
//import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Repository
//public class MachineSetRepository {
//
//    Logger logger = LoggerFactory.getLogger(MachineSetRepository.class.getName());
//    List<ExerciseSet> exerciseSets = new ArrayList<ExerciseSet>();
//
//
//    public ExerciseSet createMachineSet(ExerciseSet exerciseSet) {
//        exerciseSets.add(exerciseSet);
//        return exerciseSet;
//    }
//
//    public List<ExerciseSet> getMachineSets() {
//        return exerciseSets;
//    }
//
//    public void emptyMachines() {
//        exerciseSets.clear();
//    }
//
//    public List<ExerciseSet> getMachineSetsById(int id) {
//        List<ExerciseSet> filteredById = new ArrayList<>();
//        for (ExerciseSet exerciseSet : exerciseSets) {
//            if(exerciseSet.getId() == id){
//                filteredById.add(exerciseSet);
//            }
//        }
//        return filteredById;
//    }
//
//}
