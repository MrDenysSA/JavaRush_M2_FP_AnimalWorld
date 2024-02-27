package org.DenysSyrotiuk.organism.predators;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.DenysSyrotiuk.organism.Predator;

public class Wolf extends Predator {
    @JsonIgnore
    private static long serialUID = 1L;
    @JsonIgnore
    private final long UID = serialUID++;


    @Override
    public Wolf reproduce() {
        Wolf org = new Wolf();

        org.setIcon(getIcon());
        org.setWeight(getWeight());
        org.setMaxAmount(getMaxAmount());

        org.setSpeed(getSpeed());
        org.setMaxFoodForSaturation(getMaxFoodForSaturation());
        org.setTargetMatrix(getTargetMatrix());

        return org;
    }

    @Override
    public void move() {
    }

    @Override
    public void eat() {
    }
}
