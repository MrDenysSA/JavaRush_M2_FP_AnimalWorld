package org.DenysSyrotiuk.organism;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.DenysSyrotiuk.actionsOrganism.Eat;
import org.DenysSyrotiuk.actionsOrganism.Movable;
import org.DenysSyrotiuk.actionsOrganism.Reproducile;

import java.lang.reflect.Type;
import java.util.*;

@Getter
@Setter
public abstract class Animal extends Organism implements Movable, Eat, Reproducile {

    private int speed;
    private double maxFoodForSaturation;
    private double hunger;

    @Builder.Default
    public Map<Class<? extends Organism>, Integer> targetMatrix = new HashMap<>();

    @JsonIgnore
    private Random random = new Random();

    public void eat(Map<Type, Set<Organism>> residents) {

        targetMatrix.forEach((targetClass, probabilityEating) -> {
            Set<Organism> organisms = residents.get(targetClass);
            organisms.forEach(org -> {
                if (hunger < getMaxFoodForSaturation()) {
                    if (org.isAlive()) {
                        if (probabilityEating == 100 || probabilityEating > random.nextInt(100)) {
                            org.setAlive(false);
                            setHunger(getHunger() + org.getWeight());
                            if (hunger > maxFoodForSaturation) {
                                hunger = maxFoodForSaturation;
                            }
                        }
                    }
                }
            });
        });
    }

    public void checkSurvivability() {
        if (hunger > 0.0) {
            hunger = hunger - Math.ceil(hunger / 4);
        } else {
            setAlive(false);
        }
    }

}
