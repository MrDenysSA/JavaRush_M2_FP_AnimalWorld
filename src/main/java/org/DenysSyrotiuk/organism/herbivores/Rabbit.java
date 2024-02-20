package org.DenysSyrotiuk.organism.herbivores;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.DenysSyrotiuk.organism.Herbivore;

public class Rabbit extends Herbivore {
    @JsonIgnore
    private static long serialUID = 1L;
    @JsonIgnore
    private final long UID = serialUID++;

    @Override
    public Rabbit reproduce() {
        Rabbit rabbit = new Rabbit();

        rabbit.setIcon(getIcon());
        rabbit.setWeight(getWeight());
        rabbit.setMaxAmount(getMaxAmount());
        rabbit.setAlive(true);

        rabbit.setSpeed(getSpeed());
        rabbit.setMaxFoodForSaturation(getMaxFoodForSaturation());
        rabbit.setTargetMatrix(getTargetMatrix());

        return rabbit;
    }

    @Override
    public void eat() {
    }

    @Override
    public void move() {
    }
}
