package be.kdg.integration3.easyrep.repository.session;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SessionRepository {

    Logger logger = LoggerFactory.getLogger(SessionRepository.class);
    List<MachineSet> machineSets = new ArrayList<MachineSet>();




}
