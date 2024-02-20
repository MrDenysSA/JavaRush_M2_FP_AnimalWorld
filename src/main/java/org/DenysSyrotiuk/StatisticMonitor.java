package org.DenysSyrotiuk;

import org.DenysSyrotiuk.map.GameField;
import org.DenysSyrotiuk.organism.Organism;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class StatisticMonitor {

    public void view(GameField gameField, Long day) {
        System.out.println("*********************** \n*********************** ");
        System.out.println("Dey №: " + day + "\n");
        for (int i = 0; i < gameField.cells.length; i++) {
            for (int j = 0; j < gameField.cells[i].length; j++) {


                System.out.println("Sell №: " + i + "_" + j);
                gameField.cells[i][j].getResidents()
                        .forEach((type, organisms) -> System.out.println(type.getTypeName()
                                .substring(type.getTypeName().indexOf("organism") + 9)
                                + ": "
                                + organisms.size()));
            }
        }
        System.out.println("***********************");
    }

    public void deadAnimals(Map<Type, Set<Organism>> removeDeadMap, int i, int j, String nameMethod) {
        System.out.println("Sell №: " + i + "_" + j + " " + nameMethod);
        removeDeadMap.forEach((type, organisms) -> {
            System.out.println(type.getTypeName()
                    .substring(type.getTypeName().indexOf("organism") + 9) + ": " + organisms.size());
        });
    }

}
