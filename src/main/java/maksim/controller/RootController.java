package maksim.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import maksim.model.Phone;
import maksim.services.DataManager;

public class RootController {
    private static final String FILENAME = "phones.json";
    private final ObservableList<Phone> phones = FXCollections.observableArrayList();
    private final DataManager dataManager = new DataManager();

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
    public void initialize() {
        phonesTable.setItems(phones);
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        colorColumn.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
        callsDurationColumn.setCellValueFactory(cellData -> cellData.getValue().callsDurationProperty().asObject());
        phones.addAll(dataManager.readPhonesFromJson(FILENAME));
    }

    @FXML
    private void addPhone() {

    }

    @FXML
    private void editPhone() {

    }

    @FXML
    private void deletePhone() {

    }

    @FXML
    private void saveFile() {

    }

    @FXML
    private void showAbout() {

    }
}
