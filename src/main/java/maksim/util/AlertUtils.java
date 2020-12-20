package maksim.util;

import javafx.scene.control.Alert;

public final class AlertUtils {
    private AlertUtils() {
        throw new UnsupportedOperationException("utility class");
    }

    public static void showError(String header, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
