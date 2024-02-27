package org.DenysSyrotiuk;

import org.DenysSyrotiuk.engin.GameEngin;
import org.DenysSyrotiuk.threads.ThreadsService;

public class Main {
    public static void main(String[] args) {

        GameEngin gameEngin = new GameEngin();


        /** single Thread Start Game*/
/*        gameEngin.start();
        System.out.println("Game Over");*/

        /** Thread Start Game  */
/*        ThreadsService threadsService = new ThreadsService(gameEngin);
        threadsService.start();*/

        /** ExecutorService Start Game  */
        ThreadsService threadsService = new ThreadsService(gameEngin);
        threadsService.startExecutorService();

    }
}