package be.kdg.integration3.easyrep.service.dataProcessors;


import be.kdg.integration3.easyrep.model.sessions.MachineSet;

public interface DataProcessor {

    void process(MachineSet machineSet);

}
