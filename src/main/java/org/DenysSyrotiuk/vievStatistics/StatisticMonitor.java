package org.DenysSyrotiuk.vievStatistics;

import org.DenysSyrotiuk.map.GameField;
import org.DenysSyrotiuk.organism.Organism;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

public class StatisticMonitor {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";


    public void view(GameField gameField, Long day) {
        System.out.println("*********************** \n*********************** ");
        System.out.println(ANSI_RED + "DAY №: " + day + ANSI_RESET);
        for (int i = 0; i < gameField.cells.length; i++) {
            for (int j = 0; j < gameField.cells[i].length; j++) {
                System.out.println("Sell №: " + i + "_" + j);
                gameField.cells[i][j].getResidents()
                        .forEach((type, organisms) -> {
                            if (!organisms.isEmpty()) {
                                System.out.println(type.getTypeName()
                                        .substring(type.getTypeName().indexOf("organism") + 9)
                                        + ": "
                                        + organisms.size());
                            }
                        });
            }
        }
        System.out.println("***********************");
    }

    public void deadAnimals(Map<Type, Set<Organism>> removeDeadMap, int i, int j, String nameMethod, String colorText) {
        if (!removeDeadMap.isEmpty()) {
            System.out.println(colorSwitch(colorText) + "Sell №: " + i + "_" + j + " " + nameMethod);
            removeDeadMap.forEach((type, organisms) -> {
                System.out.println(type.getTypeName()
                        .substring(type.getTypeName().indexOf("organism") + 9)
                        + ": " + organisms.size());
            });
            System.out.print(ANSI_RESET);
        }
    }

    private String colorSwitch(String colorText) {
        if (colorText.equals("ANSI_GREEN")) {
            return ANSI_GREEN;
        } else if (colorText.equals("ANSI_BLUE")) {
            return ANSI_BLUE;
        } else {
            return ANSI_RED;
        }
    }

}
