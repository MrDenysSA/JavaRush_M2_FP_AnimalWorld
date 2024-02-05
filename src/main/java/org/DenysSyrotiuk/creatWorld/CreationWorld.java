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

public class CreationWorld {
    private SerializationYaml serializationYaml = new SerializationYaml();
    private Map<Type, Organism> deserializationOrganisms = new HashMap<>();
    public GameField gameField;

    public CreationWorld() {
        creteField(); //ПРАЦЮЄ. Десеріалізує GameField. ініціалізує пусті Cell.
        loadOrganisms(); //ПРАЦЮЄ. Десерівлізує Рослини до списку "deserializationOrganisms
        addPlantsToGameField(); //ПРАЦЮЄ з рослинми. Із списка deserializationOrganisms наповнюємо рандомно наш ГеймСвіт
        new StatisticMonitor().view(gameField);
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

    private void addPlantsToGameField() {
        Random random = new Random();
        for (int i = 0; i < gameField.cells.length; i++) {
            for (Type type : deserializationOrganisms.keySet()) {
                Organism p = (Organism) deserializationOrganisms.get(type);
                int randomCountPlants = random.nextInt(0, p.getMaxAmount());
                Set<Organism> plantSet = new HashSet<>();
                for (int j = 0; j < randomCountPlants; j++) {
                    plantSet.add(p.reproduce());
                }
                gameField.cells[i].residents.put(p.getClass(), plantSet);
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
