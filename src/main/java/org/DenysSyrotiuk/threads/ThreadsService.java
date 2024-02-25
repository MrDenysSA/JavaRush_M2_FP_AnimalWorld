package org.DenysSyrotiuk.threads;

import lombok.SneakyThrows;
import org.DenysSyrotiuk.creatWorld.GameEngin;

public class ThreadsService {

    private final AnimalsPlay animalsPlay;
    private final CheckTheEndGame checkTheEndGame;
    private final RegenerationPlants regenerationPlants;

    public ThreadsService(GameEngin gameEngin) {
        this.animalsPlay = new AnimalsPlay(gameEngin);
        this.checkTheEndGame = new CheckTheEndGame(gameEngin);
        this.regenerationPlants = new RegenerationPlants(gameEngin);
    }

    @SneakyThrows
    public void start(){
        Thread threadAnimalsPlay = new Thread(animalsPlay);
        Thread threadCheckTheEndGame = new Thread(checkTheEndGame);
        Thread threadRegenerationPlants = new Thread(regenerationPlants);

        threadAnimalsPlay.start();
        threadCheckTheEndGame.start();
        threadRegenerationPlants.start();

        threadCheckTheEndGame.join();
        regenerationPlants.setStop(true);
        animalsPlay.setStop(true);

        Thread.sleep(500);
        System.out.println("Game Over");
    }
}
