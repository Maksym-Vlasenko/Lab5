package maksim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import maksim.model.LandlinePhone;
import maksim.model.MobilePhone;
import maksim.model.Phone;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    public void start(Stage primaryStage) {
        try {
            URL rootFile = getClass().getResource("/root.fxml");
            Parent rootElement = FXMLLoader.load(rootFile);
            Scene scene = new Scene(rootElement);
            primaryStage.setTitle("Phones");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException | NullPointerException e) {
            System.err.printf("Failed to load root layout: %s%n", e.getMessage());
        }
    }

    private Phone[] createEmptyArray(int size) {
        if (size <= 0) {
            return new Phone[0];
        }
        return new Phone[Math.min(size, 100)];
    }
}
