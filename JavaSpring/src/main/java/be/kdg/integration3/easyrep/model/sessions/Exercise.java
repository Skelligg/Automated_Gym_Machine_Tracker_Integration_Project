package be.kdg.integration3.easyrep.model.sessions;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "EXERCISE")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private int exerciseId;
    @OneToMany(mappedBy = "exercise", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ExerciseSet> exerciseSetId;
    @Column(nullable = false, length = 50)
    private String exerciseName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;


    public Exercise() {
    }

    public Exercise(int exerciseId, List<ExerciseSet> exerciseSetId, String exerciseName) {
        this.exerciseId = exerciseId;
        this.exerciseSetId = exerciseSetId;
        this.exerciseName = exerciseName;
    }

    public Exercise(List<ExerciseSet> exerciseSetId, String exerciseName) {
        this.exerciseSetId = exerciseSetId;
        this.exerciseName = exerciseName;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public List<ExerciseSet> getExerciseSetId() {
        return exerciseSetId;
    }

    public void setExerciseSetId(List<ExerciseSet> exerciseSetId) {
        this.exerciseSetId = exerciseSetId;
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
                ", exerciseSetId=" + exerciseSetId +
                ", exerciseName='" + exerciseName + '\'' +
                '}';
    }
}
