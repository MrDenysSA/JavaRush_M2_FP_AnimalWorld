package org.DenysSyrotiuk.map;

import lombok.ToString;
import org.DenysSyrotiuk.organism.Organism;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ToString
public class Cell {

    private static long serialUID = 1L;

    private final long UID = serialUID++;

    public Map<Type, Set<? extends Organism>> residents = new HashMap<>();
    public Cell(){
    }

    public static long getSerialUID() {
        return serialUID;
    }

    public Map<Type, Set<? extends Organism>> getResidents() {
        return residents;
    }

}
