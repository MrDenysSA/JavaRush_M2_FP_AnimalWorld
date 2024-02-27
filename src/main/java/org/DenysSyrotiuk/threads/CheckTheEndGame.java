package org.DenysSyrotiuk.threads;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.DenysSyrotiuk.engin.GameEngin;

@Getter
@Setter
public class CheckTheEndGame implements Runnable {
    private GameEngin gameEngin;

    public CheckTheEndGame(GameEngin gameEngin) {
        this.gameEngin = gameEngin;
    }


    @SneakyThrows
    @Override
    public void run() {
        while (gameEngin.gamePlay) {
            gameEngin.checkTheEndGame();
        }
        Thread.currentThread().interrupt();

    }
}
