package org.DenysSyrotiuk.creatWorld;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.DenysSyrotiuk.vievStatistics.StatisticMonitor;
import org.DenysSyrotiuk.map.GameField;
import org.DenysSyrotiuk.organism.Animal;
import org.DenysSyrotiuk.organism.Organism;
import org.DenysSyrotiuk.organism.Plant;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class GameEngin {
    private GameField gameField;
    private boolean gamePlay = true;
    private Long day = 0L;

    Random random = new Random();
    StatisticMonitor statisticMonitor = new StatisticMonitor();

    public GameEngin() {
        if (gameField == null) {
            CreationWorld creationWorld = new CreationWorld();
            this.gameField = creationWorld.getGameField();
        }
    }

    @SneakyThrows
    public void start() {

        while (gamePlay) {
            statisticMonitor.view(gameField, day);

            eatAnimal(); // №1
            removeDeadAnimals();// №2 обязатльно нужно удалить дохлых перед размножением
            reproduceAnimals(); // №3
            moveAnimals(); // №4 могут умереть при ходах.

            regenerationPlants();
            checkTheEndGame();

            day++;
//            Thread.sleep(500);
        }
    }

    private void eatAnimal() {
        for (int i = 0; i < gameField.cells.length; i++) {
            for (int j = 0; j < gameField.cells[i].length; j++) {
                int countSellI = i;
                int countSellJ = j;
                gameField.cells[i][j].residents.forEach((type, organisms) -> {
                    for (Organism organism : organisms) {
                        if (organism instanceof Animal && (organism.isAlive())) {
                            ((Animal) organism).eat(gameField.cells[countSellI][countSellJ].residents);
                        }
                    }
                });
            }
        }
    }

    private void reproduceAnimals() {
        for (int i = 0; i < gameField.cells.length; i++) {
            for (int j = 0; j < gameField.cells[i].length; j++) {

                int countSellIndexI = i;
                int countSellIndexJ = j;
                Set<Type> types = gameField.cells[i][j].getResidents().keySet(); // Получаем список ключей (будем вытягивать списки за ключем)
                Set<Type> setAnimalType = types.stream()
                        .filter(type -> !type.getTypeName().startsWith("org.DenysSyrotiuk.organism.plants"))
                        .collect(Collectors.toSet()); // Получаем список ключей животных (будем вытягивать списки за ключем)

                Map<Type, Set<Organism>> residentsNew = new HashMap<>();
                for (Type type : setAnimalType) {
                    Set<Organism> organisms = gameField.cells[i][j].residents.get(type);
                    Set<Organism> setNewOrg = new HashSet<>();

                    int size = organisms.size();
                    int sizeNewAnimals = size % 2 == 0 || size > 1 ? size / 2 : 0;

                    if (size > 0) {
                        int maxAmount = organisms.stream().findFirst().get().getMaxAmount();
                        if (size >= maxAmount) {
                            break;
                        }

                        if (size + sizeNewAnimals > maxAmount) {
                            sizeNewAnimals = maxAmount - size;
                            sizeNewAnimals = sizeNewAnimals % 2 == 0 || size > 1 ? sizeNewAnimals : sizeNewAnimals - 1;
                        }
                    }

                    int count = 0;
                    if (sizeNewAnimals > 0) {
                        for (Organism organism : organisms) {
                            if (count == sizeNewAnimals) {
                                break;
                            }
                            Organism org = organism.reproduce();
                            setNewOrg.add(org);
                            count++;
                        }
                        residentsNew.put(type, setNewOrg);
                    }
                }

                residentsNew.forEach((type, organisms) -> organisms.forEach(organism -> {
                    gameField.cells[countSellIndexI][countSellIndexJ].residents.get(organism.getClass()).add(organism);
                }));

                statisticMonitor.deadAnimals(residentsNew, countSellIndexI, countSellIndexJ, "Reproduce animals", "ANSI_GREEN");
            }
        }
        System.out.println("");
    }

    private void regenerationPlants() {
        Map<Type, Set<Organism>> regenerationPlantsMap = new HashMap<>();
        for (int i = 0; i < gameField.getCells().length; i++) {
            for (int j = 0; j < gameField.getCells()[i].length; j++) {

                Set<Organism> setOrg = new HashSet<>();
                gameField.cells[i][j].residents.forEach((type, organisms) -> {
                    for (Organism organism : organisms) {
                        if (organism instanceof Plant) {
                            if (!organism.isAlive()) {
                                organism.setAlive(true);
                                setOrg.add(organism);
                                regenerationPlantsMap.put(organism.getClass(), setOrg);
                            }
                        }
                    }
                });
                statisticMonitor.deadAnimals(regenerationPlantsMap, i, j, "Regeneration Plants", "ANSI_GREEN");
            }
        }
        System.out.println("");
    }

    private void removeDeadAnimals() {
        Map<Type, Set<Organism>> removeDeadMap = new HashMap<>();
        for (int i = 0; i < gameField.cells.length; i++) {
            for (int j = 0; j < gameField.cells[i].length; j++) {

                gameField.cells[i][j].residents.forEach((type, organisms) -> {
                    Set<Organism> setOrg = organisms.stream()
                            .filter(organism -> !organism.isAlive())
                            .filter(Animal.class::isInstance)
                            .collect(Collectors.toSet());

                    setOrg.forEach(organism -> {
                        removeDeadMap.put(organism.getClass(), setOrg);
                        organisms.remove(organism);
                    });
                });
                statisticMonitor.deadAnimals(removeDeadMap, i, j, "Dead animals", "ANSI_RED");
            }
        }
        System.out.println("");
    }

    private void moveAnimals() {
        for (int i = 0; i < gameField.cells.length; i++) {
            for (int j = 0; j < gameField.cells[i].length; j++) {
                int yCoordSell = i;
                int xCoordSell = j;

                gameField.cells[i][j].getResidents().forEach((type, organismsS) -> {
                    Set<Organism> setOrg = organismsS.stream()
                            .filter(Organism::isAlive)
                            .filter(Animal.class::isInstance)
                            .collect(Collectors.toSet());

                    setOrg.forEach(organism -> {
                        int stepSize = random.nextInt(((Animal) organism).getSpeed());

                        int yOld = yCoordSell;
                        int xOld = xCoordSell;

                        int count = 0;
                        int yNew = yCoordSell;
                        int xNew = xCoordSell;

                        while (stepSize > count) {
                            count++;

                            if (organism.isAlive()) {
                                Set<Integer> intKayWaySet = gameField.getCells()[yNew][xNew].getNextSellMap().keySet();
                                Integer randomWay = intKayWaySet.stream().skip(random.nextInt(intKayWaySet.size())).findFirst().get();
                                int[] coordinate = gameField.getCells()[yNew][xNew].getNextSellMap().get(randomWay);
                                yNew = coordinate[0];
                                xNew = coordinate[1];

                                int size = gameField.getCells()[yNew][xNew].getResidents().get(organism.getClass()).size();
                                if (organism.getMaxAmount() > size) {
                                    ((Animal) organism).checkSurvivability();
                                    boolean add = gameField.getCells()[yNew][xNew].getResidents().get(organism.getClass()).add(organism);
                                    boolean remove = gameField.getCells()[yOld][xOld].getResidents().get(organism.getClass()).remove(organism);
                                    yOld = yNew;
                                    xOld = xNew;
/*                                    System.out.println("Organizm: " + organism.getIcon() + " " + organism.hashCode() + "\n"
                                            + "ADD done?: " + add + "\n"
                                            + "ADD y: " + yNew + "; x " + xNew + "\n"
                                            + "remove done?: " + remove + "\n"
                                            + "remove y: " + yOld + "; x " + xOld + "\n");*/
                                }
                            }
                        }
                    });
                });
            }
        }
    }

    private void checkTheEndGame() {

        boolean ruzzltat = false;

        for (int i = 0; i < gameField.cells.length; i++) {
            for (int j = 0; j < gameField.cells[i].length; j++) {
                Set<Type> types = getGameField().getCells()[i][j].getResidents().keySet();
                Set<Type> setAnimalType = types.stream().filter(type -> !type.getTypeName()
                                .startsWith("org.DenysSyrotiuk.organism.plants"))
                        .collect(Collectors.toSet());

                for (Type type : setAnimalType) {
                    boolean isAliveOrganism = getGameField().getCells()[i][j]
                            .getResidents().get(type).stream().anyMatch(Organism::isAlive);
                    if (isAliveOrganism) {
                        ruzzltat = true;
                        break;
                    }
                }
                if (ruzzltat) {
                    break;
                }
            }
            if (ruzzltat) {
                break;
            }
        }

        if (!ruzzltat) {
            gamePlay = false;
        }
    }


    @Override
    public String toString() {
        return "GameEngin{" +
                "gameField=" + gameField +
                '}';
    }
}
