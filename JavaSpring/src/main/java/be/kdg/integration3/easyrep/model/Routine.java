package be.kdg.integration3.easyrep.model;

import be.kdg.integration3.easyrep.model.sessions.Exercise;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROUTINE")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private int routine_id;

    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = false)
    private GymGoer gymGoerId;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // Changed mappedBy to refer to routine
    private List<RoutineExercise> exercises = new ArrayList<>();  // Renamed from exerciseId

    @Column(nullable = false,length = 50)
    private String routineName;

    public Routine() {
    }

    public Routine(int id, GymGoer gymGoerId, String routineName) {
        this.routine_id = id;
        this.gymGoerId = gymGoerId;
        this.routineName = routineName;
    }

    public Routine(GymGoer gymGoerId, String routineName) {
        this.gymGoerId = gymGoerId;
        this.routineName = routineName;
    }

    public List<RoutineExercise> getExercises() {
        return exercises;
    }

    public void addExercise(RoutineExercise exercise) {
        this.exercises.add(exercise);
    }

    public void setExercises(List<RoutineExercise> exercises) {
        this.exercises = exercises;
    }

    public GymGoer getGymGoerId() {
        return gymGoerId;
    }

    public void setGymGoerId(GymGoer gymGoerId) {
        this.gymGoerId = gymGoerId;
    }

    public int getId() {
        return routine_id;
    }

    public void setId(int routine_id) {
        this.routine_id = routine_id;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    @Override
    public String toString() {
        return "Routine{" +
                "id=" + routine_id +
                ", gymID=" + gymGoerId.getUserId() +
                ", routineName='" + routineName + '\'' +
                '}';
    }
}