package maksim.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import maksim.model.LandlinePhone;
import maksim.model.MobilePhone;
import maksim.model.Phone;
import maksim.util.AlertUtils;
import maksim.util.InputValidator;

public class AddPhoneController {
    @FXML
    private TextField modelField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField colorField;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private TextField callsDurationField;

    private ObservableList<Phone> dataSource;
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setDataSource(ObservableList<Phone> dataSource) {
        this.dataSource = dataSource;
    }

    @FXML
    public void initialize() {
        typeChoiceBox.getItems().addAll("MOBILE", "LANDLINE");
        typeChoiceBox.getSelectionModel().select(0);
    }

    @FXML
    private void ok() {
        try {
            String model = modelField.getText();
            String phoneNumber = phoneNumberField.getText();
            String color = colorField.getText();
            String callsDurationText = callsDurationField.getText();
            int callsDuration = Integer.parseInt(callsDurationText);
            InputValidator.validate(model, phoneNumber, color, callsDuration);

            String type = typeChoiceBox.getSelectionModel().getSelectedItem();
            Phone phone = null;
            switch (type) {
                case "MOBILE":
                    phone = new MobilePhone(model, phoneNumber, color, callsDuration);
                    break;
                case "LANDLINE":
                    phone = new LandlinePhone(model, phoneNumber, color, callsDuration);
                    break;
            }
            dataSource.add(phone);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            AlertUtils.showError("Input error", e.getMessage());
        }
    }

    @FXML
    private void cancel() {
        dialogStage.close();
    }
}
