package org.DenysSyrotiuk.map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class GameField {
    private int width;
    private int height;

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
        loadNextSellsFoMoveAnimals();
    }

    private void loadNextSellsFoMoveAnimals() {
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {

                int[] right1 = new int[2];
                int[] left2 = new int[2];
                int[] top3 = new int[2];
                int[] bottom4 = new int[2];
                Map<Integer, int[]> loadSellsMap = new HashMap<>();

                if (x == 0 && x < cells[y].length) {
                    right1[0] = y;
                    right1[1] = x + 1;
                    loadSellsMap.put(1, right1);
                } else if (x == cells[y].length - 1) {
                    left2[0] = y;
                    left2[1] = x - 1;
                    loadSellsMap.put(2, left2);
                } else if (x != 0 && x < cells[y].length) {
                    right1[0] = y;
                    right1[1] = x + 1;
                    left2[0] = y;
                    left2[1] = x - 1;
                    loadSellsMap.put(1, right1);
                    loadSellsMap.put(2, left2);
                }

                if (y == 0) {
                    bottom4[0] = y + 1;
                    bottom4[1] = x;
                    loadSellsMap.put(4, bottom4);
                } else if (y == cells.length - 1) {
                    top3[0] = y - 1;
                    top3[1] = x;
                    loadSellsMap.put(3, top3);
                } else {
                    top3[0] = y - 1;
                    top3[1] = x;
                    bottom4[0] = y + 1;
                    bottom4[1] = x;
                    loadSellsMap.put(3, top3);
                    loadSellsMap.put(4, bottom4);
                }

                cells[y][x].nextSellMap.putAll(loadSellsMap);
            }
        }
    }

}
