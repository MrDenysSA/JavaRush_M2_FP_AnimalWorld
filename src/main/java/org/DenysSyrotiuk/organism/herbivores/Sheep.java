package org.DenysSyrotiuk.organism.herbivores;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.DenysSyrotiuk.organism.Herbivore;

public class Sheep extends Herbivore {
    @JsonIgnore
    private static long serialUID = 1L;
    @JsonIgnore
    private final long UID = serialUID++;

    @Override
    public Sheep reproduce() {
        Sheep org = new Sheep();

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
    public void eat() {
    }

    @Override
    public void move() {
    }
}
