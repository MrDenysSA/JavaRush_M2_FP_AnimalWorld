package org.DenysSyrotiuk.organism.plants;

import lombok.Getter;
import lombok.Setter;
import org.DenysSyrotiuk.organism.Plant;

@Getter
@Setter
public class Daisy extends Plant {
    @Override
    public Daisy reproduce() {
        Daisy reproduce = new Daisy();
        reproduce.setIcon(getIcon());
        reproduce.setMaxAmount(getMaxAmount());
        reproduce.setWeight(getWeight());
        return reproduce;
    }
}
