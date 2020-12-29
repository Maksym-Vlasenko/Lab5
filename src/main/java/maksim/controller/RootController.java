package maksim.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import maksim.model.Phone;
import maksim.services.DataManager;
import maksim.util.AlertUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class RootController {
    private static final String FILENAME = "phones.json";
    private final ObservableList<Phone> phones = FXCollections.observableArrayList();
    private final DataManager dataManager = new DataManager();
    private Stage primaryStage;
    private File currentFile;

    @FXML
    private TableView<Phone> phonesTable;

    @FXML
    private TableColumn<Phone, String> modelColumn;

    @FXML
    private TableColumn<Phone, String> numberColumn;

    @FXML
    private TableColumn<Phone, String> colorColumn;

    @FXML
    private TableColumn<Phone, Integer> callsDurationColumn;

    @FXML
    private TableColumn<Phone, String> typeColumn;

    @FXML
    private Button addPhoneButton;

    @FXML
    private Button deletePhoneButton;

    @FXML
    private Label filenameLabel;

    @FXML
    public void initialize() {
        phonesTable.setItems(phones);
        modelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        numberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        colorColumn.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
        colorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        callsDurationColumn.setCellValueFactory(cellData -> cellData.getValue().callsDurationProperty().asObject());
        callsDurationColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setCurrentFile(File file) {
        this.currentFile = file;
    }

    @FXML
    private void addPhone() {
        try {
            URL rootFile = getClass().getResource("/addPhone.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(rootFile);
            Parent rootElement = loader.load();
            Scene scene = new Scene(rootElement);
            Stage stage = new Stage();
            stage.setTitle("Add phone");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(primaryStage);
            AddPhoneController controller = loader.getController();
            controller.setDataSource(phones);
            controller.setDialogStage(stage);
            stage.showAndWait();
        } catch (IOException | NullPointerException e) {
            String errorMessage = String.format("Failed to load add phone dialog layout: %s%n", e.getMessage());
            AlertUtils.showError("I/O error", errorMessage);
        }
    }

    @FXML
    private void deletePhone() {
        Phone selectedItem = phonesTable.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(selectedItem)) {
            phones.remove(selectedItem);
        }
    }

    @FXML
    private void createFile() {
        try {
            URL rootFile = getClass().getResource("/createFile.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(rootFile);
            Parent rootElement = loader.load();
            Scene scene = new Scene(rootElement);
            Stage stage = new Stage();
            stage.setTitle("Create file");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(primaryStage);
            CreateFileController controller = loader.getController();
            controller.setRootController(this);
            controller.setDialogStage(stage);
            stage.showAndWait();
            addPhoneButton.setDisable(false);
            deletePhoneButton.setDisable(false);
            updateCurrentFilename();
        } catch (IOException | NullPointerException e) {
            String errorMessage = String.format("Failed to load create file dialog layout: %s%n", e.getMessage());
            AlertUtils.showError("I/O error", errorMessage);
        }
    }

    @FXML
    private void openFile() {
        phones.clear();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter jsonExtensionFilter = new FileChooser.ExtensionFilter("JSON files", "*.json");
        fileChooser.getExtensionFilters().add(jsonExtensionFilter);
        currentFile = fileChooser.showOpenDialog(primaryStage);
        if (Objects.isNull(currentFile)) {
            return;
        }
        Phone[] data = dataManager.readPhonesFromJson(currentFile.getAbsolutePath());
        phones.addAll(data);
        addPhoneButton.setDisable(false);
        deletePhoneButton.setDisable(false);
        updateCurrentFilename();
    }

    @FXML
    private void closeFile() {
        phones.clear();
        currentFile = null;
        addPhoneButton.setDisable(true);
        deletePhoneButton.setDisable(true);
        updateCurrentFilename();
    }

    @FXML
    private void saveFile() {
        try {
            dataManager.savePhonesAsJson(currentFile.getAbsolutePath(), phones.toArray(new Phone[0]));
            AlertUtils.showInformation("Success", "File saved successfully");
        } catch (Exception e) {
            AlertUtils.showError("I/O error", "Failed to save file: " + e.getMessage());
        }
    }

    private void updateCurrentFilename() {
        if (Objects.nonNull(currentFile)) {
            filenameLabel.setText(currentFile.getAbsolutePath());
        } else {
            filenameLabel.setText("");
        }
    }
}
