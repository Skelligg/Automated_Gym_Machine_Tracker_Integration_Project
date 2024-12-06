package be.kdg.integration3.easyrep.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ROUTINE_EXERCISE")
public class RoutineExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exerciseId")
    private int exerciseid;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;  // Many exercises belong to one routine

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public int getExerciseid() {
        return exerciseid;
    }
}
