package org.DenysSyrotiuk.organism.plants;

import org.DenysSyrotiuk.organism.Plant;

public class Daisy extends Plant {
    @Override
    public Daisy reproduce() {

        Daisy daisy = new Daisy();

        daisy.setIcon(getIcon());
        daisy.setWeight(getWeight());
        daisy.setMaxAmount(getMaxAmount());
        daisy.setAlive(true);

        return daisy;
    }
}
