package org.DenysSyrotiuk.organism.predators;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.DenysSyrotiuk.organism.Predator;

public class Wolf extends Predator {
    @JsonIgnore
    private static long serialUID = 1L;
    @JsonIgnore
    private final long UID = serialUID++;
    @Override
    public void move() {

    }

    @Override
    public Wolf reproduce() {
        Wolf reproduce = new Wolf();
        reproduce.setCell(getCell());
        reproduce.setIcon(getIcon());
//        reproduce.setMaxWeight(getMaxWeight());
        reproduce.setWeight(getWeight());
        reproduce.setMaxAmount(getMaxAmount());
        reproduce.setSpeed(getSpeed());
        reproduce.setMaxFood(getMaxFood());
        reproduce.setAlive(true);
        reproduce.setTargetMatrix(getTargetMatrix());
        return reproduce;
    }
}
