package org.DenysSyrotiuk.threads;

import org.DenysSyrotiuk.creatWorld.GameEngin;
import org.DenysSyrotiuk.vievStatistics.StatisticMonitor;

public class StatisticTread implements Runnable{
    private GameEngin gameEngin;
    StatisticMonitor statisticMonitor = new StatisticMonitor();
    Thread current = Thread.currentThread();

    public StatisticTread(GameEngin gameEngin){
        this.gameEngin = gameEngin;
    }

    @Override
    public void run() {
        while (!current.isInterrupted()) {
            statisticMonitor.view(gameEngin.getGameField(), 111L);
        }
    }
}
