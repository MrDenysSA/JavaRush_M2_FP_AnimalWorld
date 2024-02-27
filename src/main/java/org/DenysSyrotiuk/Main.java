package org.DenysSyrotiuk;

import org.DenysSyrotiuk.creatWorld.GameEngin;
import org.DenysSyrotiuk.creatWorld.SerializationYaml;
import org.DenysSyrotiuk.organism.herbivores.*;
import org.DenysSyrotiuk.organism.plants.Daisy;
import org.DenysSyrotiuk.organism.plants.Fern;
import org.DenysSyrotiuk.organism.predators.*;
import org.DenysSyrotiuk.threads.AnimalsPlay;
import org.DenysSyrotiuk.threads.CheckTheEndGame;
import org.DenysSyrotiuk.threads.RegenerationPlants;
import org.DenysSyrotiuk.threads.ThreadsService;

import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Daisy daisy = new Daisy();
        daisy.setIcon("🌼");
        daisy.setWeight(2);
        daisy.setMaxAmount(100);
        daisy.setAlive(true);

        Fern fern = new Fern();
        fern.setIcon("🌿");
        fern.setWeight(1);
        fern.setMaxAmount(200);
        fern.setAlive(true);

        Rabbit rabbit = new Rabbit();
        rabbit.setIcon("\uD83D\uDC07");
        rabbit.setWeight(2);
        rabbit.setMaxAmount(150);
        rabbit.setSpeed(2);
        rabbit.setMaxFoodForSaturation(0.45);
        rabbit.setHunger(0.45);
        rabbit.targetMatrix.put(Daisy.class, 100);
        rabbit.targetMatrix.put(Fern.class, 100);

        Wolf wolf = new Wolf();
        wolf.setIcon("\uD83D\uDC3A");
        wolf.setWeight(50);
        wolf.setMaxAmount(30);
        wolf.setSpeed(3);
        wolf.setHunger(4.0);
        wolf.setMaxFoodForSaturation(8);
        wolf.setAlive(true);
        wolf.targetMatrix.put(Horse.class, 10);
        wolf.targetMatrix.put(Deer.class, 15);
        wolf.targetMatrix.put(Rabbit.class, 60);
        wolf.targetMatrix.put(Mouse.class, 80);
        wolf.targetMatrix.put(Goat.class, 60);
        wolf.targetMatrix.put(Sheep.class, 70);
        wolf.targetMatrix.put(Boar.class, 15);
        wolf.targetMatrix.put(Buffalo.class, 10);
        wolf.targetMatrix.put(Duck.class, 40);

        Bear bear = new Bear();
        bear.setIcon("\uD83D\uDC3B");
        bear.setWeight(500);
        bear.setMaxAmount(5);
        bear.setSpeed(2);
        bear.setHunger(40.0);
        bear.setMaxFoodForSaturation(80);
        bear.setAlive(true);
        bear.targetMatrix.put(Boa.class, 80);
        bear.targetMatrix.put(Horse.class, 40);
        bear.targetMatrix.put(Deer.class, 80);
        bear.targetMatrix.put(Rabbit.class, 80);
        bear.targetMatrix.put(Mouse.class, 90);
        bear.targetMatrix.put(Goat.class, 70);
        bear.targetMatrix.put(Sheep.class, 70);
        bear.targetMatrix.put(Boar.class, 50);
        bear.targetMatrix.put(Buffalo.class, 20);
        bear.targetMatrix.put(Duck.class, 10);

        Boa boa = new Boa();
        boa.setIcon("\uD83D\uDC0D");
        boa.setWeight(15);
        boa.setMaxAmount(30);
        boa.setSpeed(1);
        boa.setHunger(1.5);
        boa.setMaxFoodForSaturation(3);
        boa.setAlive(true);
        boa.targetMatrix.put(Fox.class, 15);
        boa.targetMatrix.put(Rabbit.class, 20);
        boa.targetMatrix.put(Mouse.class, 40);
        boa.targetMatrix.put(Duck.class, 10);

        Fox fox = new Fox();
        fox.setIcon("\uD83E\uDD8A");
        fox.setWeight(8);
        fox.setMaxAmount(30);
        fox.setSpeed(2);
        fox.setHunger(1.0);
        fox.setMaxFoodForSaturation(2);
        fox.setAlive(true);
        fox.targetMatrix.put(Rabbit.class, 70);
        fox.targetMatrix.put(Mouse.class, 90);
        fox.targetMatrix.put(Duck.class, 60);
        fox.targetMatrix.put(Caterpillar.class, 40);

        Eagle eagle = new Eagle();
        eagle.setIcon("\uD83E\uDD85");
        eagle.setWeight(6);
        eagle.setMaxAmount(20);
        eagle.setSpeed(3);
        eagle.setHunger(1.0);
        eagle.setMaxFoodForSaturation(1);
        eagle.setAlive(true);
        eagle.targetMatrix.put(Fox.class, 10);
        eagle.targetMatrix.put(Rabbit.class, 90);
        eagle.targetMatrix.put(Mouse.class, 90);
        eagle.targetMatrix.put(Duck.class, 80);

        Horse horse = new Horse();
        horse.setIcon("\uD83D\uDC0E");
        horse.setWeight(400);
        horse.setMaxAmount(20);
        horse.setSpeed(4);
        horse.setHunger(30.0);
        horse.setMaxFoodForSaturation(60);
        horse.setAlive(true);
        horse.targetMatrix.put(Daisy.class, 100);
        horse.targetMatrix.put(Fern.class, 100);

        Deer deer = new Deer();
        deer.setIcon("\uD83E\uDD8C");
        deer.setWeight(300);
        deer.setMaxAmount(20);
        deer.setSpeed(4);
        deer.setHunger(25.0);
        deer.setMaxFoodForSaturation(50);
        deer.setAlive(true);
        deer.targetMatrix.put(Daisy.class, 100);
        deer.targetMatrix.put(Fern.class, 100);

        Mouse mouse = new Mouse();
        mouse.setIcon("\uD83D\uDC01");
        mouse.setWeight(0.05);
        mouse.setMaxAmount(500);
        mouse.setSpeed(1);
        mouse.setHunger(0.01);
        mouse.setMaxFoodForSaturation(0.01);
        mouse.setAlive(true);
        mouse.targetMatrix.put(Caterpillar.class, 90);
        mouse.targetMatrix.put(Daisy.class, 100);
        mouse.targetMatrix.put(Fern.class, 100);

        Goat goat = new Goat();
        goat.setIcon("\uD83D\uDC10");
        goat.setWeight(60);
        goat.setMaxAmount(140);
        goat.setSpeed(3);
        goat.setHunger(5.0);
        goat.setMaxFoodForSaturation(10);
        goat.setAlive(true);
        goat.targetMatrix.put(Daisy.class, 100);
        goat.targetMatrix.put(Fern.class, 100);

        Sheep sheep = new Sheep();
        sheep.setIcon("\uD83D\uDC11");
        sheep.setWeight(70);
        sheep.setMaxAmount(140);
        sheep.setSpeed(3);
        sheep.setHunger(7.5);
        sheep.setMaxFoodForSaturation(15);
        sheep.setAlive(true);
        sheep.targetMatrix.put(Daisy.class, 100);
        sheep.targetMatrix.put(Fern.class, 100);

        Boar boar = new Boar();
        boar.setIcon("\uD83D\uDC17");
        boar.setWeight(400);
        boar.setMaxAmount(50);
        boar.setSpeed(2);
        boar.setHunger(25.0);
        boar.setMaxFoodForSaturation(50);
        boar.setAlive(true);
        boar.targetMatrix.put(Mouse.class, 50);
        boar.targetMatrix.put(Caterpillar.class, 90);
        boar.targetMatrix.put(Daisy.class, 100);
        boar.targetMatrix.put(Fern.class, 100);

        Buffalo buffalo = new Buffalo();
        buffalo.setIcon("\uD83D\uDC03");
        buffalo.setWeight(700);
        buffalo.setMaxAmount(10);
        buffalo.setSpeed(3);
        buffalo.setHunger(50.0);
        buffalo.setMaxFoodForSaturation(100);
        buffalo.setAlive(true);
        buffalo.targetMatrix.put(Daisy.class, 100);
        buffalo.targetMatrix.put(Fern.class, 100);

        Duck duck = new Duck();
        duck.setIcon("\uD83E\uDD86");
        duck.setWeight(1);
        duck.setMaxAmount(200);
        duck.setSpeed(4);
        duck.setHunger(0.15);
        duck.setMaxFoodForSaturation(0.15);
        duck.setAlive(true);
        duck.targetMatrix.put(Caterpillar.class, 90);
        duck.targetMatrix.put(Daisy.class, 100);
        duck.targetMatrix.put(Fern.class, 100);

        Caterpillar caterpillar = new Caterpillar();
        caterpillar.setIcon("\uD83D\uDC1B");
        caterpillar.setWeight(0.01);
        caterpillar.setMaxAmount(1000);
        caterpillar.setSpeed(0);
        caterpillar.setHunger(0.0);
        caterpillar.setMaxFoodForSaturation(0.0);
        caterpillar.setAlive(true);
        caterpillar.targetMatrix.put(Daisy.class, 100);
        caterpillar.targetMatrix.put(Fern.class, 100);












        SerializationYaml serializationYaml = new SerializationYaml();
        serializationYaml.pushOrganism(daisy);
        serializationYaml.pushOrganism(fern);
        serializationYaml.pushOrganism(rabbit);
        serializationYaml.pushOrganism(wolf);
        serializationYaml.pushOrganism(bear);
        serializationYaml.pushOrganism(boa);
        serializationYaml.pushOrganism(fox);
        serializationYaml.pushOrganism(eagle);
        serializationYaml.pushOrganism(horse);
        serializationYaml.pushOrganism(deer);
        serializationYaml.pushOrganism(mouse);
        serializationYaml.pushOrganism(goat);
        serializationYaml.pushOrganism(sheep);
        serializationYaml.pushOrganism(boar);
        serializationYaml.pushOrganism(buffalo);
        serializationYaml.pushOrganism(duck);
        serializationYaml.pushOrganism(caterpillar);


        System.out.println(wolf.getClass());









        GameEngin gameEngin = new GameEngin();


        /** single Thread Start Game*/
        gameEngin.start();
        System.out.println("Game Over");

        /** Thread Start Game  */
/*        ThreadsService threadsService = new ThreadsService(gameEngin);
        threadsService.start();*/

        /** ExecutorService Start Game  */
/*        ThreadsService threadsService = new ThreadsService(gameEngin);
        threadsService.startExecutorService();*/
    }
}