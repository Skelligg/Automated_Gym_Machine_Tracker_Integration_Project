package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SetRepositoryImpl implements SetRepository {

    private static List<MachineSet> machineSets = new ArrayList<MachineSet>();

    @Override
    public MachineSet createSet(MachineSet machineSet){
        machineSet.setId(machineSets.size());
        machineSets.add(machineSet);
        return machineSet;
    }

    @Override
    public List<MachineSet> getSets(){
        return machineSets;
    }

    @Override
    public void emptyList(){
        machineSets.clear();
    }
}
