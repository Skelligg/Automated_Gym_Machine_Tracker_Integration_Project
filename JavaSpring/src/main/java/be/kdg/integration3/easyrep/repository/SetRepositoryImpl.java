package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SetRepositoryImpl implements SetRepository {

    private static List<ExerciseSet> exerciseSets = new ArrayList<ExerciseSet>();

    @Override
    public ExerciseSet createSet(ExerciseSet exerciseSet){
//        exerciseSet.setId(exerciseSets.size());
        exerciseSets.add(exerciseSet);
        return exerciseSet;
    }

    @Override
    public List<ExerciseSet> getSets(){
        return exerciseSets;
    }

    @Override
    public void emptyList(){
        exerciseSets.clear();
    }
}
