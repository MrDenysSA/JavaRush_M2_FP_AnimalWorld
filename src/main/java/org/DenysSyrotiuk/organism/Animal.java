package org.DenysSyrotiuk.organism;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.DenysSyrotiuk.actionsOrganism.Eat;
import org.DenysSyrotiuk.actionsOrganism.Movable;
import org.DenysSyrotiuk.map.Cell;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public abstract class Animal extends Organism implements Movable, Eat {
//    private String  icon;
//    private int     weight;     // Вага однієї тварини, кг
//    private int     maxAmount;  // Максимальна кількість тварин в клітинці.
//    private int     maxWeight;  // Щоб тварина відновлювала вагу коли їла інших тварин
//    private boolean isAlive = true; // TODO: якщо false то тваринка вибуває і видаляється
    private int speed;
    private double maxFood;      // Скільки кілограмів їжі потрібно тварині для повного насичення
    private double hunger = 0.0;

    @Builder.Default
    public Map<Class<? extends Organism>, Integer> targetMatrix = new HashMap<>(); // Список тварин яких можна їсти.
    @JsonIgnore
    private Cell cell;

    public Map<Type, Set<? extends Organism>> eat(Map<Type, Set<? extends Organism>> residents) {
        Random random = new Random();

        targetMatrix.forEach((aClass, integer) -> {
            Set<? extends Organism> organisms = residents.get(aClass);
            organisms.forEach(org -> {
                if (hunger < getMaxFood()) { // если наше животное голодное, то ищем жертву
                    if (org.isAlive()) {    // Ищем жертву среди живых
                        if (integer == 100 || integer > random.nextInt(100)) { // если выполнилось условие, то догнали жертву
                            org.setAlive(false); // убиваем животное
                            setHunger(getHunger() + org.getWeight());
//                            System.out.println("Мене зʼїли:" + org.getIcon());
                        }
                    }
                }
            });
        });

        return residents;
    }
}
