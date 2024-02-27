package org.DenysSyrotiuk.threads;

import lombok.SneakyThrows;
import org.DenysSyrotiuk.engin.GameEngin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsService {

    private final AnimalsPlay animalsPlay;
    private final CheckTheEndGame checkTheEndGame;
    private final RegenerationPlants regenerationPlants;

    private final GameEngin gameEngin;

    public ThreadsService(GameEngin gameEngin) {
        this.gameEngin = gameEngin;
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
        Thread.sleep(1000);
        System.out.println("Game Over");
    }

    @SneakyThrows
    public void startExecutorService(){
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(animalsPlay);
        executor.execute(checkTheEndGame);
        executor.execute(regenerationPlants);

        while (gameEngin.gamePlay) {
            // waiting for the end of the game
        }
        executor.shutdown();
        Thread.sleep(1000);
        System.out.println("Game Over");
    }
}
