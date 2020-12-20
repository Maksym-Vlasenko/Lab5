package maksim.services;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import maksim.model.Phone;
import maksim.util.AlertUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DataManager {
    private final ObjectMapper objectMapper;

    public DataManager () {
        this.objectMapper = new ObjectMapper();
    }

    public Phone[] readPhonesFromJson(String filename) {
        try {
            Path filePath = Paths.get(filename);
            if (Files.notExists(filePath)) {
                return new Phone[0];
            }
            File inputFile = filePath.toFile();
            return objectMapper.readValue(inputFile, Phone[].class);
        } catch (IOException e) {
            String errorMessage = String.format("Failed to read phones: %s%n", e.getMessage());
            AlertUtils.showError("I/O error", errorMessage);
            return new Phone[0];
        }
    }

    public boolean savePhonesAsJson(String filename, Phone[] phones) {
        Path filePath = Paths.get(filename);
        try {
            File outputFile = Files.exists(filePath) ? filePath.toFile() : Files.createFile(filePath).toFile();
            SequenceWriter writer = objectMapper.writer().writeValuesAsArray(outputFile);
            for (Phone phone : phones) {
                writer.write(phone);
            }
            writer.close();
            return true;
        } catch (IOException e) {
            String errorMessage = String.format("Failed to save phones: %s%n", e.getMessage());
            AlertUtils.showError("I/O error", errorMessage);
            return false;
        }
    }
}
