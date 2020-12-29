package maksim.util;

import javafx.scene.control.Alert;

public final class AlertUtils {
    private AlertUtils() {
        throw new UnsupportedOperationException("utility class");
    }

    public static void showError(String header, String errorMessage) {
        Alert alert = createBasic(Alert.AlertType.ERROR, "Error");
        alert.setHeaderText(header);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public static void showInformation(String header, String errorMessage) {
        Alert alert = createBasic(Alert.AlertType.INFORMATION, "Info");
        alert.setHeaderText(header);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    private static Alert createBasic(Alert.AlertType type, String title) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        return alert;
    }
}
