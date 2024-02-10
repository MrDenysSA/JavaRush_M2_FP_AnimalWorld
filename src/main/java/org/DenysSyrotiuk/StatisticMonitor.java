package org.DenysSyrotiuk;

import org.DenysSyrotiuk.map.GameField;

public class StatisticMonitor {
    private int countDey = 1;
    public void view(GameField gameField) {
        System.out.println("***********************");
        System.out.println("Dey №: " + countDey);
        for (int i = 0; i < gameField.cells.length; i++) {
            System.out.println("Sell №: " + i);
            gameField.cells[i].getResidents()
                    .forEach((type, organisms) -> System.out.println(type.getTypeName()
                            .substring(type.getTypeName().indexOf("organism") + 9) + ": " + organisms.size()));
            System.out.println("");
        }
        System.out.println("***********************");
        countDey++;
    }

}
