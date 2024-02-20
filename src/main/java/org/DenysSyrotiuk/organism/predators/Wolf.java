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
        Wolf wolf = new Wolf();

        wolf.setIcon(getIcon());
        wolf.setWeight(getWeight());
        wolf.setMaxAmount(getMaxAmount());
        wolf.setAlive(true);

        wolf.setSpeed(getSpeed());
        wolf.setMaxFoodForSaturation(getMaxFoodForSaturation());
        wolf.setTargetMatrix(getTargetMatrix());

        return wolf;
    }

    @Override
    public void move() {
    }

    @Override
    public void eat() {
    }
}
