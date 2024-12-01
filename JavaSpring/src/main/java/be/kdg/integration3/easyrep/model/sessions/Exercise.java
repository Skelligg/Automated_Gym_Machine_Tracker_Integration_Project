package be.kdg.integration3.easyrep.model.sessions;

import java.util.List;

public class Exercise {
    private String name;
    private List<ExerciseSet> sets;
    private String previousSet;


    public Exercise(String name, List<ExerciseSet> sets, String previousSet) {
        this.name = name;
        this.sets = sets;
        this.previousSet = previousSet;
    }


    public Exercise(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExerciseSet> getSets() {
        return sets;
    }

    public void setSets(List<ExerciseSet> sets) {
        this.sets = sets;
    }

    public String getPreviousSet() {
        return previousSet;
    }

    public void setPreviousSet(String previousSet) {
        this.previousSet = previousSet;
    }
}
