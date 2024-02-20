package org.DenysSyrotiuk.organism;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.DenysSyrotiuk.actionsOrganism.Reproducile;

@Getter
@Setter
@ToString
public abstract class Organism implements Reproducile {
    private String icon;
    private double weight;
    private int maxAmount;
    private boolean isAlive = true;
}
