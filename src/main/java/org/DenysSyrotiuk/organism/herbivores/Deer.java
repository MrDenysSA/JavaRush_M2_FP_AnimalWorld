package org.DenysSyrotiuk.organism.herbivores;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.DenysSyrotiuk.organism.Herbivore;

public class Deer extends Herbivore {
    @JsonIgnore
    private static long serialUID = 1L;
    @JsonIgnore
    private final long UID = serialUID++;

    @Override
    public Deer reproduce() {
        Deer org = new Deer();

        org.setIcon(getIcon());
        org.setWeight(getWeight());
        org.setMaxAmount(getMaxAmount());

        org.setSpeed(getSpeed());
        org.setMaxFoodForSaturation(getMaxFoodForSaturation());
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
