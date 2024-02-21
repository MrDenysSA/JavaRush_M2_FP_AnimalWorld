package org.DenysSyrotiuk.creatWorld;

import lombok.Getter;
import org.DenysSyrotiuk.map.GameField;
import org.DenysSyrotiuk.organism.Organism;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CreationWorld {
    private final SerializationYaml serializationYaml = new SerializationYaml();
    private final Map<Type, Organism> deserializationOrganisms = new HashMap<>();
    @Getter
    private GameField gameField;

    public CreationWorld() {
        creteField();
        loadOrganisms();
        addOrganismsToGameField();
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

    @Override
    public String toString() {
        return "CreationWorld{" +
                "gameField=" + gameField +
                '}';
    }
}
