package org.DenysSyrotiuk;

import org.DenysSyrotiuk.creatWorld.CreationWorld;
import org.DenysSyrotiuk.creatWorld.SerializationYaml;
import org.DenysSyrotiuk.organism.herbivores.Rabbit;
import org.DenysSyrotiuk.organism.predators.Wolf;
import org.DenysSyrotiuk.organism.plants.Daisy;
import org.DenysSyrotiuk.organism.plants.Fern;

public class Main {
    public static void main(String[] args) {

/*        Wolf wolf = new Wolf();
        wolf.setIcon("\uD83D\uDC3A");
        wolf.setMaxWeight(50);
        wolf.setWeight(50);
        wolf.setMaxAmount(30);
        wolf.setSpeed(3);
        wolf.setMaxFood(8);
        wolf.targetMatrix.put(Rabbit.class,60);

        Rabbit rabbit = new Rabbit();
        rabbit.setIcon("\uD83D\uDC07");
        rabbit.setMaxWeight(2);
        rabbit.setWeight(2);
        rabbit.setMaxAmount(150);
        rabbit.setSpeed(2);
        rabbit.setMaxFood(0.45);
        rabbit.targetMatrix.put(Daisy.class,100);
        rabbit.targetMatrix.put(Fern.class,100);

        System.out.println(rabbit.getClass().getSuperclass().getSimpleName().toLowerCase());

        SerializationYaml serializationYaml = new SerializationYaml();

        serializationYaml.pushOrganism(wolf);
        serializationYaml.pushOrganism(rabbit);*/


        CreationWorld world = new CreationWorld();


        System.out.println("FIN");
    }
}