package org.DenysSyrotiuk.map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.DenysSyrotiuk.organism.Organism;
import org.DenysSyrotiuk.organism.Plant;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@ToString
public class Cell {

    private static long serialUID = 1L;
    private final long UID = serialUID++;

    public Map<Type, Set<? extends Organism>> residents = new HashMap<>();

    public Cell(){

    }

}
