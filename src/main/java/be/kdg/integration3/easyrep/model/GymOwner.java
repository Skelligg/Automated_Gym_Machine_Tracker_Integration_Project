package be.kdg.integration3.easyrep.domain;

import java.util.ArrayList;
import java.util.List;

public class GymOwner extends User {
    private List<Machine> machines = new ArrayList<>();

    public GymOwner(long id, String firstName, String lastName, String emailAddress, String password) {
        super(id, firstName, lastName, emailAddress, password);
    }

    public void addMachine(Machine newMachine) {
        machines.add(newMachine);
    }

    public void setMaintenanceAlarm(Machine machine) {
        machine.alarmSet();
    }

    public String retrieveUsageData() {
        return "yehaw";
    }
}
