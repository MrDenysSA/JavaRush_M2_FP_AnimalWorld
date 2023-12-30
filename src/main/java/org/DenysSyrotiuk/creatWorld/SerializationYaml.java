package org.DenysSyrotiuk.creatWorld;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.DenysSyrotiuk.map.GameField;
import org.DenysSyrotiuk.organism.Organism;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Цей клас необхідний для Серіалазаціх і десеріалізації класів
 */
public class SerializationYaml {
    String pathToResources = "src/main/resources/";
    String pathGameField = "src/main/resources/map/gameField.yaml";
    YAMLMapper yamlMapper = new YAMLMapper();

    public <T> T pull(String fileDirectory, Class<T> type) {
        try (FileInputStream fileInputStream = new FileInputStream(fileDirectory)) {
            return yamlMapper.readValue(fileInputStream, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void push(String fileDirectory, Object object) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileDirectory)) {
            yamlMapper.writeValue(fileOutputStream, object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void pushOrganism(Organism organism) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(pathToResources)
                .append(organism.getClass().getSuperclass().getSimpleName().toLowerCase())
                .append("s/")
                .append(organism.getClass().getSimpleName())
                .append(".yaml");
        push(stringBuilder.toString(), organism);
    }

    public void pushGameField(GameField gameField) {
        push(pathGameField, gameField);
    }

}
