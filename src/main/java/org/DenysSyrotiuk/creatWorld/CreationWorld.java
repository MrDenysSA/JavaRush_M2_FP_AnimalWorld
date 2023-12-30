package org.DenysSyrotiuk.creatWorld;

import lombok.SneakyThrows;
import org.DenysSyrotiuk.map.GameField;
import org.DenysSyrotiuk.organism.Organism;
import org.DenysSyrotiuk.organism.Plant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CreationWorld {
    public SerializationYaml serializationYaml = new SerializationYaml();
    public String pathGameField = "src/main/resources/map/gameField.yaml";
    public Map<Type, Organism> deserializationOrganisms = new HashMap<>();
    public GameField gameField;

    public CreationWorld() {
        creteField(); //ПРАЦЮЄ. Десерівлізує GameField. ініціалізує пусті Cell.
        loadPlants(); //ПРАЦЮЄ. Десерівлізує Рослини до списку "deserializationOrganisms"
        addPlantsToGameField();
    }

    public void creteField() {
        gameField = serializationYaml.pull(pathGameField, GameField.class);
        gameField.initializationCell();  // создаем поле и звполняем его Селами пока пустыми
        statistics("creteField() : ");
    }

    public void loadPlants() {
        Path directory = Path.of("src/main/resources/plants/");
        try (DirectoryStream<Path> files = Files.newDirectoryStream(directory)) {
            for (Path p : files) {
                String nameOrganismClass =
                        "org.DenysSyrotiuk.organism."
                                + p.toString().substring(p.toString().lastIndexOf("/")
                                + 1, p.toString().lastIndexOf(".yaml"));
                deserializationOrganisms.put(
                        Class.forName(nameOrganismClass),
                        (Plant) serializationYaml.pull(p.toString(), Class.forName(nameOrganismClass)));
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPlantsToGameField() {
        Random random = new Random();
        for (int i = 0; i < gameField.cells.length; i++) { // <бігаемо по кожному спсиску

            for (Type type : deserializationOrganisms.keySet()) {
                Plant p = (Plant) deserializationOrganisms.get(type);
                int randomCountPlants = random.nextInt(0, p.getMaxAmount());
                Set<Organism> plantSet = new HashSet<>();
                for (int j = 0; j < randomCountPlants; j++) {
                    plantSet.add(p.reproduce());
                }
                gameField.cells[i].residents.put(p.getClass(),plantSet);
            }

        }
        System.out.println(gameField); // Удалить потом
    }


    public void statistics(String where) {
        System.out.println(where + gameField);
    }

    @Override
    public String toString() {
        return "CreationWorld{" +
                "gameField=" + gameField +
                '}';
    }
}
