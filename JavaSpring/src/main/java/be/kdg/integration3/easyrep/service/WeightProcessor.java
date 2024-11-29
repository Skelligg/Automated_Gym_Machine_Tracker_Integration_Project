package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.sessions.MachineSet;
import org.springframework.stereotype.Component;

@Component
public class WeightProcessor implements DataProcessor {

    @Override
    public void process(MachineSet machineSet) {
        // Convert weight from grams to kilograms
        double weightInKg = machineSet.getWeightCount() / 1000.0;

        // Round to the nearest 0.25
        double roundedWeight = roundToNearestQuarter(weightInKg);

        // Set the rounded weight back to the machineSet object
        machineSet.setWeightCount(roundedWeight);
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
