package org.DenysSyrotiuk.threads;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.DenysSyrotiuk.engin.GameEngin;

@Getter
@Setter
public class AnimalsPlay implements Runnable {
    private GameEngin gameEngin;
    private boolean isStop = false;

    public AnimalsPlay(GameEngin gameEngin) {
        this.gameEngin = gameEngin;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (gameEngin.gamePlay) {
            gameEngin.animalPlay();
        }
        Thread.currentThread().interrupt();
    }
}
