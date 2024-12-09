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

//    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // Changed mappedBy to refer to routine
//    private List<Exercise> exercises = new ArrayList<>();  // Renamed from exerciseId

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

//    public List<Exercise> getExercises() {
//        return exercises;
//    }
//
//    public void addExercise(Exercise exercise) {
//        this.exercises.add(exercise);
//    }
//
//    public void setExercises(List<Exercise> exercises) {
//        this.exercises = exercises;
//    }

    public int getId() {
        return routine_id;
    }

    public void setId(int id) {
        this.routine_id = id;
    }

    public GymGoer getGymGoerId() {
        return gymGoerId;
    }

    public void setGymGoerId(GymGoer gymGoerId) {
        this.gymGoerId = gymGoerId;
    }

    public int getRoutine_id() {
        return routine_id;
    }

    public void setRoutine_id(int routine_id) {
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
                ", gymID=" + gymGoerId +
                ", routineName='" + routineName + '\'' +
                '}';
    }
}