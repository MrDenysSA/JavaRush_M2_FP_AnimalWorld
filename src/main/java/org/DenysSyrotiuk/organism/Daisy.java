package org.DenysSyrotiuk.organism;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Daisy extends Plant {
    @Override
    public Daisy reproduce() {
        Daisy reproduce = new Daisy();
        reproduce.setIcon(getIcon());
        reproduce.setMaxAmount(getMaxAmount());
        reproduce.setMaxWeight(getMaxWeight());
        return reproduce;
    }
}
