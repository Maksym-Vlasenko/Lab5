package maksim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import maksim.controller.RootController;
import maksim.model.Phone;
import maksim.util.AlertUtils;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    public void start(Stage primaryStage) {
        try {
            URL rootFile = getClass().getResource("/root.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(rootFile);
            Parent rootElement = loader.load();
            RootController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            Scene scene = new Scene(rootElement);
            primaryStage.setTitle("Phones");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException | NullPointerException e) {
            String errorMessage = String.format("Failed to load root layout: %s%n", e.getMessage());
            AlertUtils.showError("I/O error", errorMessage);
        }
    }

    private Phone[] createEmptyArray(int size) {
        if (size <= 0) {
            return new Phone[0];
        }
        return new Phone[Math.min(size, 100)];
    }
}
