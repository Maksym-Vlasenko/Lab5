package maksim.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import maksim.util.AlertUtils;
import maksim.util.InputValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFileController {
    @FXML
    private TextField filenameField;

    private Stage dialogStage;
    private RootController rootController;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @FXML
    private void ok() {
        String filename = filenameField.getText();
        try {
            InputValidator.validateString(filename);
            filename = filename.endsWith(".json") ? filename : filename.concat(".json");
            Path filepath = Paths.get(filename);
            if (Files.exists(filepath)) {
                AlertUtils.showError("Error", "File already exists");
                return;
            }
            File file = Files.createFile(Paths.get(filename)).toFile();
            rootController.setCurrentFile(file);
            dialogStage.close();
        } catch (IllegalArgumentException | IOException e) {
            AlertUtils.showError("Error", "I/O error: " + e.getMessage());
        }
    }

    @FXML
    private void cancel() {
        dialogStage.close();
    }
}
