package org.DenysSyrotiuk.map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.DenysSyrotiuk.organism.Organism;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ToString
@Getter
@Setter
public class Cell {

    private static long serialUID = 1L;

    private final long UID = serialUID++;

    public Map<Type, Set<Organism>> residents = new HashMap<>();

    public Map<Integer, int[]> nextSellMap = new HashMap<>();
    public Cell(){
    }


}
