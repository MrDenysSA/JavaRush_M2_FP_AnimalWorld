package org.DenysSyrotiuk.organism.herbivores;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.DenysSyrotiuk.organism.Herbivore;

public class Rabbit extends Herbivore {
    @JsonIgnore
    private static long serialUID = 1L;
    @JsonIgnore
    private final long UID = serialUID++;
    @Override
    public void move() {

    }

    @Override
    public Rabbit reproduce() {
            Rabbit reproduce = new Rabbit();
            reproduce.setCell(getCell());
            reproduce.setIcon(getIcon());
            reproduce.setMaxWeight(getMaxWeight());
            reproduce.setWeight(getWeight());
            reproduce.setMaxAmount(getMaxAmount());
            reproduce.setSpeed(getSpeed());
            reproduce.setMaxFood(getMaxFood());
            reproduce.setAlive(true);
            reproduce.setTargetMatrix(getTargetMatrix());
            return reproduce;
    }
}
