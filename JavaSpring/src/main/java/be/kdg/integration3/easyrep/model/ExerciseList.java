package be.kdg.integration3.easyrep.model;

import jakarta.persistence.*;

@Entity
@Table(name = "EXERCISE_LIST")
public class ExerciseList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
