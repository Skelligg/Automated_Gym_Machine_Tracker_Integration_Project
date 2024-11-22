package be.kdg.integration3.easyrep.model.sessions;

import be.kdg.integration3.easyrep.model.Machine;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MachineSession {
    private long id;
    private Machine machine;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<Set> sets = new ArrayList<>();
}
