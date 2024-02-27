package org.DenysSyrotiuk.organism;

import lombok.*;
import org.DenysSyrotiuk.actionsOrganism.Eat;
import org.DenysSyrotiuk.actionsOrganism.Movable;
import org.DenysSyrotiuk.actionsOrganism.Reproducile;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public abstract class Animal extends Organism implements Movable, Eat, Reproducile {

    private int speed;
    private double maxFoodForSaturation;
    private double hunger;

    public Map<Class<? extends Organism>, Integer> targetMatrix = new HashMap<>();

    public void eat(Map<Type, Set<Organism>> residents) {

        targetMatrix.forEach((targetClass, probabilityEating) -> {
            Set<Organism> organisms = residents.get(targetClass);
            organisms.forEach(org -> {
                if (hunger < getMaxFoodForSaturation()) {
                    if (org.isAlive()) {
                        if (probabilityEating == 100 || probabilityEating > ThreadLocalRandom.current().nextInt(100)) {
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
        if (hunger > 0.00) {
            if (maxFoodForSaturation == 0.0) {
                hunger = hunger - 1;
            }else {
                hunger = hunger - (maxFoodForSaturation / 2);
            }

        } else {
            setAlive(false);
        }
    }

}
