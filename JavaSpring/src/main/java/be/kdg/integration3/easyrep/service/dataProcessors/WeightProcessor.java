package be.kdg.integration3.easyrep.service.dataProcessors;

import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import org.springframework.stereotype.Component;

@Component
public class WeightProcessor implements DataProcessor {

    @Override
    public void process(ExerciseSet exerciseSet) {
        // Convert weight from grams to kilograms
        double weightInKg = exerciseSet.getWeightCount() / 1000.0;

        // Round to the nearest 0.25
        double roundedWeight = roundToNearestQuarter(weightInKg);

        // Set the rounded weight back to the exerciseSet object
        exerciseSet.setWeightCount(roundedWeight);
    }

    /**
     * Round a weight to the nearest 0.25
     * weight The weight in kg
     * weight rounded to the nearest 0.25
     */
    private double roundToNearestQuarter(double weight) {
        return Math.round(weight * 4) / 4.0;
    }
}
