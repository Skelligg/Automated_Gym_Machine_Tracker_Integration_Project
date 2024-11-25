package be.kdg.integration3.easyrep.model;

import java.util.ArrayList;
import java.util.List;

public class GymStaff {
    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private List<Machine> machines = new ArrayList<>();

    public GymStaff(int id, String firstName, String lastName, String emailAddress, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
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
