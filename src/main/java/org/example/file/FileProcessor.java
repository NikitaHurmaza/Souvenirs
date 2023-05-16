package org.example.file;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.Souvenirs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.logic.Logic;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    private final String fileName = "souvenirs";
    private final String fileExtension = ".json";
    private final String directory = "files/";
    private final String filePath = directory + fileName + fileExtension;
    public void writeFile(Logic souv) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(filePath), souv.getSouvenirs());
        } catch (IOException ex) {

            ex.printStackTrace();
        }

    }

    public List<Souvenirs> readFile() {

        File file = new File(filePath);
        List<Souvenirs> souvenirs = new ArrayList<>();

        if (file.length() == 0) {

            return souvenirs;
        }

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            souvenirs = objectMapper.readValue(new File(filePath), new TypeReference<List<Souvenirs>>() {
            });

        } catch (IOException err) {

            err.printStackTrace();
        }

        return souvenirs;

    }

}
