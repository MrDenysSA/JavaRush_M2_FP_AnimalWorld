package org.DenysSyrotiuk.creatWorld;

import org.DenysSyrotiuk.StatisticMonitor;
import org.DenysSyrotiuk.map.GameField;
import org.DenysSyrotiuk.organism.Animal;
import org.DenysSyrotiuk.organism.Organism;
import org.DenysSyrotiuk.organism.Plant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class CreationWorld {
    private SerializationYaml serializationYaml = new SerializationYaml();
    private Map<Type, Organism> deserializationOrganisms = new HashMap<>();
    public GameField gameField;

    public Random random = new Random();

    public CreationWorld() {
        creteField(); //ПРАЦЮЄ. Десеріалізує GameField. ініціалізує пусті Cell.
        loadOrganisms(); //ПРАЦЮЄ. Десерівлізує Рослини до списку "deserializationOrganisms
        addOrganismsToGameField(); //ПРАЦЮЄ  Із списка deserializationOrganisms наповнюємо рандомно наш ГеймСвіт
        loadNextSellsFoMoveAnimals();//ПРАЦЮЄ

        new StatisticMonitor().view(gameField);//ПРАЦЮЄ
        moveAnimals();//ПРАЦЮЄ


//        eatAnimal();//ПРАЦЮЄ
//        removeDeadAnimals();//ПРАЦЮЄ
//        reproduceAnimals(); //ПРАЦЮЄ
//        regenerationPlants();//ПРАЦЮЄ
//        moveAnimals();


        new StatisticMonitor().view(gameField);//ПРАЦЮЄ
        System.out.println("Hia");
    }

    private void creteField() {
        String pathGameField = "src/main/resources/map/gameField.yaml";
        gameField = serializationYaml.pull(pathGameField, GameField.class);
        gameField.initializationCell();
    }

    private void loadOrganisms() {
        String pathToResourcesClass;
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0 -> pathToResourcesClass = "src/main/resources/plants/";
                case 1 -> pathToResourcesClass = "src/main/resources/herbivores/";
                default -> pathToResourcesClass = "src/main/resources/predators/";
            }
            Path directory = Path.of(pathToResourcesClass);
            try (DirectoryStream<Path> files = Files.newDirectoryStream(directory)) {
                for (Path p : files) {
                    String nameOrganismClass = "org.DenysSyrotiuk.organism."
                            + p.toString().substring(
                                    p.toString().indexOf("src") + 19,
                                    p.toString().lastIndexOf(".yaml"))
                            .replace('/', '.');
                    deserializationOrganisms.put(
                            Class.forName(nameOrganismClass),
                            (Organism) serializationYaml.pull(p.toString(), Class.forName(nameOrganismClass)));
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addOrganismsToGameField() {
        Random random = new Random();
        for (int i = 0; i < gameField.cells.length; i++) {
            for (int j = 0; j < gameField.cells[i].length; j++) {
                for (Type type : deserializationOrganisms.keySet()) {
                    Organism organism = deserializationOrganisms.get(type);
                    int randomCountOrganism = random.nextInt(0, organism.getMaxAmount());
                    Set<Organism> organismSet = new HashSet<>();
                    for (int k = 0; k < randomCountOrganism; k++) {
                        organismSet.add(organism.reproduce());
                    }
                    gameField.cells[i][j].residents.put(organism.getClass(), organismSet);
                }
            }
        }
    }

    private void loadNextSellsFoMoveAnimals() {
        for (int y = 0; y < gameField.getCells().length; y++) {
            for (int x = 0; x < gameField.getCells()[y].length; x++) {

                int[] right1 = new int[2];
                int[] left2 = new int[2];
                int[] top3 = new int[2];
                int[] bottom4 = new int[2];
                Map<Integer, int[]> loadSellsMap = new HashMap<>();

                if (x == 0 && x < gameField.getCells()[y].length) { // если х ноль то он в начале (то слева нет клетки)
                    right1[1] = x + 1;
                    right1[0] = y;
                    loadSellsMap.put(1, right1);
                } else if (x == gameField.getCells()[y].length - 1) { // если x дошол до конца ( то справа  нет клетки)
                    left2[1] = x - 1;
                    left2[0] = y;
                    loadSellsMap.put(2, left2);
                } else if (x != 0 && x < gameField.getCells()[y].length) {
                    right1[1] = x + 1;
                    right1[0] = y;
                    left2[1] = x - 1;
                    left2[0] = y;
                    loadSellsMap.put(1, right1);
                    loadSellsMap.put(2, left2);
                }

                if (y == 0 && y < gameField.getCells().length) {
                    bottom4[0] = y + 1;
                    bottom4[1] = x;
                    loadSellsMap.put(4, bottom4);
                } else if (y == gameField.getCells().length - 1) { // если x дошол до конца ( то справа  нет клетки)
                    top3[0] = y - 1;
                    top3[1] = x;
                    loadSellsMap.put(3, top3);
                } else if (y != 0 && x < gameField.getCells().length) {
                    top3[0] = y - 1;
                    top3[1] = x;
                    bottom4[0] = y + 1;
                    bottom4[1] = x;
                    loadSellsMap.put(3, top3);
                    loadSellsMap.put(4, bottom4);
                }

                gameField.getCells()[y][x].nextSellMap.putAll(loadSellsMap);
            }
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
                new StatisticMonitor().deadAnimals(regenerationPlantsMap, i, j, "Regeneration Plants");
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
                new StatisticMonitor().deadAnimals(removeDeadMap, i, j, "Dead animals");
            }
        }
        System.out.println("");
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

                new StatisticMonitor().deadAnimals(residentsNew, countSellIndexI, countSellIndexJ, "Reproduce animals");
            }
        }
        System.out.println("");
    }

    private void moveAnimals() {
        for (int i = 0; i < gameField.cells.length; i++) {
            for (int j = 0; j < gameField.cells[i].length; j++) {
                int yCoordSell = i;
                int xCoordSell = j;

                gameField.cells[i][j].getResidents().forEach((type, organisms) -> {
                    Set<Organism> setOrg = organisms.stream()
                            .filter(Animal.class::isInstance)
                            .collect(Collectors.toSet());

                    setOrg.forEach(organism -> {
                        int stepSize = random.nextInt(((Animal) organism).getSpeed());
                        int count = 0;
                        int x = xCoordSell;
                        int y = yCoordSell;

                        while (stepSize > count) {
                            count++;
                            Set<Integer> intKayWaySet = gameField.getCells()[y][x].getNextSellMap().keySet();
                            Integer randomWay = intKayWaySet.stream().skip(random.nextInt(intKayWaySet.size())).findFirst().get();
                            int[] coordinate = gameField.getCells()[y][x].getNextSellMap().get(randomWay);
                            y = coordinate[0];
                            x = coordinate[1];

                            gameField.getCells()[y][x].getResidents().get(organism.getClass()).add(organism);
                            organisms.remove(organism);
                        }
                    });
                });
            }
        }
    }

    @Override
    public String toString() {
        return "CreationWorld{" +
                "gameField=" + gameField +
                '}';
    }
}
