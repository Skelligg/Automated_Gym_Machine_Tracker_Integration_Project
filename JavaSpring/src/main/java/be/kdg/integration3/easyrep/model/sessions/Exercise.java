package be.kdg.integration3.easyrep.model.sessions;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.Routine;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EXERCISE")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private int exerciseId;

    @OneToMany(mappedBy = "exercise", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ExerciseSet> exerciseSets = new ArrayList<>();  // List of exercise sets associated with this exercise

    @Column(nullable = false, length = 50)
    private String exerciseName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;  // Many exercises belong to one routine

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;  // Many exercises belong to one session

    @OneToOne
    @JoinColumn(name = "machine_id", nullable = false)
    private Machine machine;


    public Exercise() {
    }

    public Exercise(int exerciseId, List<ExerciseSet> exerciseSetId, String exerciseName) {
        this.exerciseId = exerciseId;
        this.exerciseSets = exerciseSetId;
        this.exerciseName = exerciseName;
    }

    public Exercise(List<ExerciseSet> exerciseSetId, String exerciseName) {
        this.exerciseSets = exerciseSetId;
        this.exerciseName = exerciseName;
    }


    public List<ExerciseSet> getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(List<ExerciseSet> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public List<ExerciseSet> getExerciseSetId() {
        return exerciseSets;
    }

    public void setExerciseSetId(List<ExerciseSet> exerciseSetId) {
        this.exerciseSets = exerciseSetId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "exerciseId=" + exerciseId +
                ", exerciseSetId=" + exerciseSets +
                ", exerciseName='" + exerciseName + '\'' +
                '}';
    }
}
