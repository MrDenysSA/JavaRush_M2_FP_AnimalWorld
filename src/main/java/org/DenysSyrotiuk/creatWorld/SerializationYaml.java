package org.DenysSyrotiuk.creatWorld;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.Getter;
import org.DenysSyrotiuk.map.GameField;
import org.DenysSyrotiuk.organism.Organism;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
@Getter
public class SerializationYaml {
    private final String pathToResources = "src/main/resources/";
    private final String pathGameField = "src/main/resources/map/gameField.yaml";
    private final YAMLMapper yamlMapper = new YAMLMapper();

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
        String stringBuilder = pathToResources +
                organism.getClass().getSuperclass().getSimpleName().toLowerCase() +
                "s/" +
                organism.getClass().getSimpleName() +
                ".yaml";
        push(stringBuilder, organism);
    }

    public void pushGameField(GameField gameField) {
        push(pathGameField, gameField);
    }

}
