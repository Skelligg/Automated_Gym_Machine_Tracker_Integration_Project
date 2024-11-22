package be.kdg.integration3.easyrep.model;

import java.util.ArrayList;
import java.util.List;

public class GymGoer {
    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private List<Routine> routines = new ArrayList<>();

    public GymGoer(int id, String firstName, String lastName, String emailAddress, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }


    public List<Routine> getRoutines() {
        return routines;
    }

    public void addToRoutine(Routine routine) {
        routines.add(routine);
    }
}
