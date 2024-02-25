package org.DenysSyrotiuk;

import org.DenysSyrotiuk.creatWorld.GameEngin;
import org.DenysSyrotiuk.creatWorld.SerializationYaml;
import org.DenysSyrotiuk.organism.herbivores.Rabbit;
import org.DenysSyrotiuk.organism.plants.Daisy;
import org.DenysSyrotiuk.organism.plants.Fern;
import org.DenysSyrotiuk.organism.predators.Wolf;
import org.DenysSyrotiuk.threads.AnimalsPlay;
import org.DenysSyrotiuk.threads.CheckTheEndGame;
import org.DenysSyrotiuk.threads.RegenerationPlants;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Daisy daisy = new Daisy();
        daisy.setIcon("🌼");
        daisy.setWeight(1.5);
        daisy.setMaxAmount(88);
        daisy.setAlive(true);

        Fern fern = new Fern();
        fern.setIcon("🌿");
        fern.setWeight(1);
        fern.setMaxAmount(99);
        fern.setAlive(true);

        Rabbit rabbit = new Rabbit();
        rabbit.setIcon("\uD83D\uDC07");
        rabbit.setWeight(2);
        rabbit.setMaxAmount(150);
        rabbit.setSpeed(2);
        rabbit.setMaxFoodForSaturation(0.45);
        rabbit.setHunger(0.0);
        rabbit.targetMatrix.put(Daisy.class, 100);
        rabbit.targetMatrix.put(Fern.class, 100);

        Wolf wolf = new Wolf();
        wolf.setIcon("\uD83D\uDC3A");
        wolf.setWeight(50);
        wolf.setMaxAmount(30);
        wolf.setSpeed(3);
        wolf.setHunger(0.0);
        wolf.setMaxFoodForSaturation(8);
        wolf.targetMatrix.put(Rabbit.class, 60);

        SerializationYaml serializationYaml = new SerializationYaml();
        serializationYaml.pushOrganism(daisy);
        serializationYaml.pushOrganism(fern);
        serializationYaml.pushOrganism(rabbit);
        serializationYaml.pushOrganism(wolf);
        System.out.println(wolf.getClass());

        GameEngin gameEngin = new GameEngin();




        /** Работает checkTheEndGame*/
        CheckTheEndGame checkTheEndGame = new CheckTheEndGame(gameEngin);
        Thread threadCheckTheEndGame = new Thread(checkTheEndGame);
        threadCheckTheEndGame.start();
        /** до сюда*/

        RegenerationPlants regenerationPlants = new RegenerationPlants(gameEngin);
        Thread threadRegenerationPlants = new Thread(regenerationPlants);
        threadRegenerationPlants.start();

        AnimalsPlay animalsPlay = new AnimalsPlay(gameEngin);
        Thread threadAnimalsPlay = new Thread(animalsPlay);
        threadAnimalsPlay.start();

//        StatisticTread statisticTread = new StatisticTread(gameEngin);
//        Thread threadStatistic = new Thread(statisticTread);
//        threadStatistic.start();

        /** Должно работать */
        threadCheckTheEndGame.join();
        regenerationPlants.setStop(true);
        animalsPlay.setStop(true);



        Thread.sleep(500);
        System.out.println("Main fin");
    }
}