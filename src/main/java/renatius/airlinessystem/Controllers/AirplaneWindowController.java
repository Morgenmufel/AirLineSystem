package renatius.airlinessystem.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
        if(!name_column.getText().isEmpty()) airPlane.setPlaneName(name_field.getText().trim());
        if(!model_field.getText().isEmpty()) airPlane.setAirPlaneModel(model_field.getText().trim());
        airPlane.setAirPlaneStatus("FREE");
        airPlaneService.addAirPlane(airPlane);
        ViewAirplanes();
    };

    public void EditAirplane(){
        AirPlaneServiceImpl airPlaneService = new AirPlaneServiceImpl();
        airplaneTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null)  {
                AirPlane selected_plane = airplaneTableView.getSelectionModel().getSelectedItem();
                selected_plane.setPlaneName(name_field.getText().trim());
                selected_plane.setAirPlaneModel(model_field.getText().trim());
                airPlaneService.updateAirPlane(selected_plane);
                error_edit_label.setText("Самолёт изменён!");
            }
        });
        ViewAirplanes();
    };

    public void DeleteAirplane(){
        AirPlaneServiceImpl airPlaneService = new AirPlaneServiceImpl();
        airplaneTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                AirPlane selected_airplane = airplaneTableView.getSelectionModel().getSelectedItem();
                airPlaneService.deleteAirPlane(selected_airplane);
                error_delete_label.setText("Самолёт удалён!");
            }
        });
        ViewAirplanes();
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
