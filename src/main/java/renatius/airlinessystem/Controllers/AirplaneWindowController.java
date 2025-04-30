package renatius.airlinessystem.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;
import renatius.airlinessystem.Entity.Crew.FlightCrew;
import renatius.airlinessystem.services.impl.AirPlaneServiceImpl;

import java.io.IOException;

public class AirplaneWindowController {

    @FXML
    private TableView<AirPlane> airplaneTableView;

    @FXML
    private TableColumn<AirPlane,String> name_column;

    @FXML
    private TableColumn<AirPlane,String> model_column;

    @FXML
    private TableColumn<AirPlane,String> status_column;

    @FXML
    private TableColumn<AirPlane,String> flight_column;

    @FXML
    private Button view_button;

    @FXML
    private TextField name_field;

    @FXML
    private TextField model_field;

    @FXML
    private Label error_add_label;

    @FXML
    private Button add_button;

    @FXML
    private TextField rename_field;

    @FXML
    private TextField remodel_field;

    @FXML
    private Label error_edit_label;

    @FXML
    private Button edit_button;

    @FXML
    private Button delete_button;

    @FXML
    private Label error_delete_label;

    @FXML
    private Button exitButton;

    @FXML
    public void initialize() {
        AirPlaneServiceImpl airPlaneService = new AirPlaneServiceImpl();
        ObservableList<AirPlane> airPlanes = FXCollections.observableList(airPlaneService.getAllAirPlanes());
        airplaneTableView.setItems(airPlanes);
        name_column.setCellValueFactory(new PropertyValueFactory<>("planeName"));
        model_column.setCellValueFactory(new PropertyValueFactory<>("airPlaneModel"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("airPlaneStatus"));
        flight_column.setCellValueFactory(new PropertyValueFactory<>("flight"));
        ////////////////////////////////////////////////////////////
        name_column.setCellFactory(col -> {
            TableCell<AirPlane, String> cell = new TableCell<>() {
                private final Text text = new Text();

                {
                    text.wrappingWidthProperty().bind(col.widthProperty().subtract(10));
                    setGraphic(text);
                    setPrefHeight(Control.USE_COMPUTED_SIZE);
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        text.setText("");
                    } else {
                        text.setText(item);
                    }
                }
            };
            return cell;
        });
    }

    public void ViewAirplanes(){
        AirPlaneServiceImpl airPlaneService = new AirPlaneServiceImpl();
        ObservableList<AirPlane> airPlanes = FXCollections.observableList(airPlaneService.getAllAirPlanes());
        airplaneTableView.getItems().clear();
        airplaneTableView.getItems().addAll(airPlanes);
    };

    public void AddAirplane(){
        AirPlaneServiceImpl airPlaneService = new AirPlaneServiceImpl();
        AirPlane airPlane = new AirPlane();
        if(!name_field.getText().isEmpty()) {
            airPlane.setPlaneName(name_field.getText().trim());
        }
        else{
            error_add_label.setText("Please enter a name for the airplane");
            return;
        }
        if(!model_field.getText().isEmpty()) {
            airPlane.setAirPlaneModel(model_field.getText().trim());
        }else {
            error_add_label.setText("Please enter a model for the airplane");
            return;
        }
        airPlane.setAirPlaneStatus("FREE");
        airPlaneService.addAirPlane(airPlane);
        ViewAirplanes();
    };

    public void EditAirplane(){
        AirPlane selectedPlane = airplaneTableView.getSelectionModel().getSelectedItem();
        AirPlaneServiceImpl airPlaneService = new AirPlaneServiceImpl();
        if (selectedPlane == null) {
            error_edit_label.setText("Ошибка: выберите самолёт из таблицы!");
            return;
        }
        String newName = rename_field.getText().trim();
        String newModel = remodel_field.getText().trim();
        if (newName.isEmpty() && newModel.isEmpty()) {
            error_edit_label.setText("Измените хотя бы одно поле");
            return;
        }
        try {
            if(!newName.isEmpty() && newModel.isEmpty()){
                selectedPlane = airPlaneService.getAirPlaneById(selectedPlane.getAirplane_id());
                selectedPlane.setPlaneName(newName);
            }
            else if(newName.isEmpty() && !newModel.isEmpty()){
                selectedPlane = airPlaneService.getAirPlaneById(selectedPlane.getAirplane_id());
                selectedPlane.setAirPlaneModel(newModel);
            }
            else{
                selectedPlane = airPlaneService.getAirPlaneById(selectedPlane.getAirplane_id());
                selectedPlane.setAirPlaneModel(newModel);
                selectedPlane.setPlaneName(newName);
            }
            airPlaneService.updateAirPlane(selectedPlane);
            ViewAirplanes();
            error_edit_label.setText("Данные самолёта успешно изменены!");
            rename_field.clear();
            remodel_field.clear();
        } catch (Exception e) {
            error_edit_label.setText("Ошибка при обновлении: " + e.getMessage());
        }

    };

    public void DeleteAirplane(){
        AirPlaneServiceImpl airPlaneService = new AirPlaneServiceImpl();
        AirPlane airPlane = airplaneTableView.getSelectionModel().getSelectedItem();
        if (airPlane == null) {
            error_delete_label.setText("Выберите самолёт");
            return;
        }
        airPlaneService.deleteAirPlane(airPlane);
        ViewAirplanes();
        error_edit_label.setText("Самолёт успешно удалён");
    };

    public void logout(){
        exitButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/ActionSelection.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent parent=loader.getRoot();
        Stage stage=new Stage();
        stage.requestFocus();
        stage.setScene(new Scene(parent));
        stage.show();
    }




}
