package org.DenysSyrotiuk.serialization;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SerializationYaml {
    YAMLMapper yamlMapper = new YAMLMapper();
    public void push(String fileDirectory, Object object){
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileDirectory)){
            yamlMapper.writeValue(fileOutputStream,object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T pull(String fileDirectory, Class<T> type) {
        try (FileInputStream fileInputStream = new FileInputStream(fileDirectory)) {
            return yamlMapper.readValue(fileInputStream, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
