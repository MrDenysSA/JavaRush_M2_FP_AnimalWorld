package org.DenysSyrotiuk.organism;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.DenysSyrotiuk.actionsOrganism.Eat;
import org.DenysSyrotiuk.actionsOrganism.Movable;
import org.DenysSyrotiuk.actionsOrganism.Reproducile;
import org.DenysSyrotiuk.map.Cell;

import java.lang.reflect.Type;
import java.util.*;

@Getter
@Setter
@ToString
public abstract class Animal extends Organism implements Movable, Eat, Reproducile {
    private int speed;
    private double maxFood;      // Скільки кілограмів їжі потрібно тварині для повного насичення
    private double hunger = 0.0; // Голод

    @Builder.Default
    public Map<Class<? extends Organism>, Integer> targetMatrix = new HashMap<>(); // Список тварин яких можна їсти.
    @JsonIgnore
    private Cell cell;

    public void eat(Map<Type, Set<? extends Organism>> residents) {
        Random random = new Random();

        targetMatrix.forEach((aClass, integer) -> {
            Set<? extends Organism> organisms = residents.get(aClass);
            organisms.forEach(org -> {
                if (hunger < getMaxFood()) { // если наше животное голодное, то ищем жертву
                    if (org.isAlive()) {    // Ищем жертву среди живых
                        if (integer == 100 || integer > random.nextInt(100)) { // если выполнилось условие, то догнали жертву
                            org.setAlive(false); // убиваем животное
                            setHunger(getHunger() + org.getWeight());
                            if (hunger > maxFood) { // Щоб тваринка не переїдала.
                                hunger = maxFood;
                            }
                        }
                    }
                }
            });
        });
    }

}
