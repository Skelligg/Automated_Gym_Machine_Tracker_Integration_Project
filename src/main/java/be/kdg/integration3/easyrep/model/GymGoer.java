package be.kdg.integration3.easyrep.domain;

import be.kdg.integration3.easyrep.domain.sessions.RoutineSession;

import java.util.ArrayList;
import java.util.List;

public class GymGoer extends User {
    private List<Routine> routines = new ArrayList<>();
    private List<GymGoer> friends = new ArrayList<>();
    private Routine activeRoutine = new Routine();

    public GymGoer(long id, String firstName, String lastName, String emailAddress, String password) {
        super(id, firstName, lastName, emailAddress, password);
    }

    public void addFriend(GymGoer newFriend) {
        friends.add(newFriend);
    }

    public void beginRoutineSession(Routine routine) {
        activeRoutine = routine;
        activeRoutine.beginNewSession();
    }

    public void endRoutineSession() {
        activeRoutine.endCurrentSession();
    }
}
