package org.DenysSyrotiuk.organism.predators;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.DenysSyrotiuk.organism.Predator;

public class Bear extends Predator {
    @JsonIgnore
    private static long serialUID = 1L;
    @JsonIgnore
    private final long UID = serialUID++;


    @Override
    public Bear reproduce() {
        Bear org = new Bear();

        org.setIcon(getIcon());
        org.setWeight(getWeight());
        org.setMaxAmount(getMaxAmount());
        org.setAlive(true);

        org.setSpeed(getSpeed());
        org.setMaxFoodForSaturation(getMaxFoodForSaturation());
        org.setHunger(getHunger());
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
