package org.DenysSyrotiuk.threads;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.DenysSyrotiuk.creatWorld.GameEngin;
@Getter
@Setter
public class RegenerationPlants implements Runnable{

    private GameEngin gameEngin;
    private boolean isStop = false;

    public RegenerationPlants(GameEngin gameEngin) {
            this.gameEngin = gameEngin;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (gameEngin.gamePlay) {
            gameEngin.regenerationPlants();
            Thread.sleep(1);
        }
        Thread.currentThread().interrupt();
    }
}
