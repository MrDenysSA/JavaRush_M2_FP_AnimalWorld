package org.DenysSyrotiuk.map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@ToString
public class GameField {
    private int width = 4;
    private int height;

    @JsonIgnore
    public Cell[] cells;

    public GameField() {
    }

    public void initializationCell() {
        this.cells = new Cell[width];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell();
        }
    }
}
