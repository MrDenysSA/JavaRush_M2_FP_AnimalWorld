package org.DenysSyrotiuk.organism.plants;

import org.DenysSyrotiuk.organism.Plant;

public class Fern extends Plant {
    @Override
    public Fern reproduce() {
        Fern fern = new Fern();

        fern.setIcon(getIcon());
        fern.setWeight(getWeight());
        fern.setMaxAmount(getMaxAmount());
        fern.setAlive(true);

        return fern;
    }
}
