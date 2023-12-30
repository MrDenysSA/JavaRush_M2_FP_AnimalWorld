package org.DenysSyrotiuk;

import org.DenysSyrotiuk.creatWorld.CreationWorld;
import org.DenysSyrotiuk.map.Cell;
import org.DenysSyrotiuk.map.GameField;
import org.DenysSyrotiuk.organism.Daisy;
import org.DenysSyrotiuk.organism.Fern;
import org.DenysSyrotiuk.organism.Organism;
import org.DenysSyrotiuk.organism.Plant;

import java.lang.reflect.Type;
import java.util.*;

public class Main {
    public static void main(String[] args) {
/*        Map<Type, Set<? extends Organism>> fernSet = new HashMap<>();

        Fern fern = new Fern();
        fern.setIcon("\uD83C\uDF3F");
        fern.setMaxWeight(1);
        fern.setMaxAmount(100);

        Daisy daisy = new Daisy();
        daisy.setIcon("\uD83C\uDF3C");
        daisy.setMaxWeight(2);
        daisy.setMaxAmount(50);

        Set<Fern> ferns = new HashSet<>();
        Set<Daisy> daisies = new HashSet<>();

        Random random = new Random();
        int randomCountPlants = random.nextInt(0, fern.getMaxAmount());
        for (int i = 0; i < randomCountPlants; i++) {
            ferns.add(fern.reproduce());
        }
        int randomCountPlants2 = random.nextInt(0, daisy.getMaxAmount());
        for (int i = 0; i < randomCountPlants2; i++) {
            daisies.add(daisy.reproduce());
        }

        fernSet.put(Fern.class,ferns );
        fernSet.put(Daisy.class,daisies );



        Cell cell = new Cell();
        cell.residents.putAll(fernSet);*/


/*        GameField gameField = new GameField();
        gameField.initializationCell();

        System.out.println(gameField.cells.length);

        for (int i = 0; i < gameField.cells.length; i++) {
            gameField.cells[i] =
        }*/



        CreationWorld world = new CreationWorld();

        System.out.println("FIN");
    }
}