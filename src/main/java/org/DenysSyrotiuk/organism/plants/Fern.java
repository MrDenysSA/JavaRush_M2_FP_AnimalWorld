package org.DenysSyrotiuk.organism.plants;

import lombok.Getter;
import lombok.Setter;
import org.DenysSyrotiuk.organism.Plant;

@Getter
@Setter
public class Fern extends Plant {
    @Override
    public Fern reproduce() {
        Fern reproduce = new Fern();
        reproduce.setIcon(getIcon());
        reproduce.setMaxAmount(getMaxAmount());
        reproduce.setMaxWeight(getMaxWeight());
        return reproduce;
    }
}
