package org.DenysSyrotiuk.organism.predators;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.DenysSyrotiuk.organism.Predator;

public class Boa extends Predator {
    @JsonIgnore
    private static long serialUID = 1L;
    @JsonIgnore
    private final long UID = serialUID++;
    @Override
    public void eat() {

    }

    @Override
    public void move() {

    }

    @Override
    public Boa reproduce() {
        Boa org = new Boa();

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
}
