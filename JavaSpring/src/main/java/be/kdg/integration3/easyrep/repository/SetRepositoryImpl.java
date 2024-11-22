package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.Set;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SetRepositoryImpl implements SetRepository {

    private static List<Set> sets = new ArrayList<Set>();

    @Override
    public Set createSet(Set set){
//        machineSet.setId(machineSets.size());
        sets.add(set);
        return set;
    }

    @Override
    public List<Set> getSets(){
        return sets;
    }

    @Override
    public void emptyList(){
        sets.clear();
    }
}
