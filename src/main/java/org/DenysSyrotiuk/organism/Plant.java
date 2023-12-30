package org.DenysSyrotiuk.organism;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
public abstract class Plant extends Organism {
    private String icon;
    private int maxWeight; // Вага. Після кожного такту регенерує свою вагу.
    private int maxAmount;  // Максимальна кількість на одній клітинці.

}
