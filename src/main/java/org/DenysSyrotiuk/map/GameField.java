package org.DenysSyrotiuk.map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class GameField {
    private int width = 4;
    private int height = 4;

    @JsonIgnore
    public Cell[][] cells;

    public GameField() {
    }

    public void initializationCell() {
        this.cells = new Cell[width][height];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }
    }
}
