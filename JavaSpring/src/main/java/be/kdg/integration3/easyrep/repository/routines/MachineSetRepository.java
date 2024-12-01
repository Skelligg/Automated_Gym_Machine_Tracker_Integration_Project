package be.kdg.integration3.easyrep.repository.routines;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class MachineSetRepository {

    Logger logger = LoggerFactory.getLogger(MachineSetRepository.class.getName());
    List<MachineSet> machineSets = new ArrayList<MachineSet>();


    public MachineSet createMachineSet(MachineSet machineSet) {
        machineSets.add(machineSet);
        return machineSet;
    }


    public List<MachineSet> getMachineSets() {
        return machineSets;
    }

    public void emptyMachines() {
        machineSets.clear();
    }

    public List<MachineSet> getMachineSetsById(int id) {
        List<MachineSet> filteredById = new ArrayList<>();
        for (MachineSet machineSet : machineSets) {
            if(machineSet.getSetId() == id){
                filteredById.add(machineSet);
            }
        }
        return filteredById;
    }


}
