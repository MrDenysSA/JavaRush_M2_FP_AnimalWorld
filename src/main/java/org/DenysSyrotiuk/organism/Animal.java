package org.DenysSyrotiuk.organism;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.DenysSyrotiuk.actionsOrganism.Eat;
import org.DenysSyrotiuk.actionsOrganism.Movable;
import org.DenysSyrotiuk.map.Cell;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public abstract class Animal extends Organism implements Movable, Eat {

    private String  icon;       // Іконка
    private int     maxWeight;  // Щоб тварина відновлювала вагу коли їла інших тварин
    private int     weight;     // Вага цієї однієї тварини, кг в момент. Зменьшується с кожнім тактом. Помирає якщо 0 кг.
//    private int     maxAmount;  // Для початкового наповнення і для того щоб не ходили на клітинку це тварин максимум
    private int     speed;      // Швидкість переміщення, не більше ніж клітинок за хід
    private double  maxFood;
    private boolean isAlive = true; // TODO: якщо false то тваринка вибуває і видаляється

    @Builder.Default
    public Map<Class<? extends Organism>, Integer> targetMatrix = new HashMap<>(); // Список тварин яких можна їсти.
    @JsonIgnore
    private Cell cell;

}
