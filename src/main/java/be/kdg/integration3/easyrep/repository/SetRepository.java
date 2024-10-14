package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.Set;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SetRepository {

    private static List<Set> sets = new ArrayList<Set>();

    public Set createSet(Set set){
        sets.add(set);
        return set;
    }
}
