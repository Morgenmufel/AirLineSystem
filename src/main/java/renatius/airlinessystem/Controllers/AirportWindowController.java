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
import renatius.airlinessystem.Entity.Enum.WeatherStatus;
import renatius.airlinessystem.Entity.GroundUnit.Airport;
import renatius.airlinessystem.services.impl.AirportServiceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class AirportWindowController {

    @FXML
    private TableView<Airport> AirportTableView;

    @FXML
    private TableColumn<Airport, String> name_column;

    @FXML
    private TableColumn<Airport, String> country_column;

    @FXML
    private TableColumn<Airport, String> status_column;

    @FXML
    private TextField name_field;

    @FXML
    private TextField country_field;

    @FXML
    private ChoiceBox<String> choose_status_box;

    @FXML
    private Label error_add_label;

    @FXML
    private Button add_button;

    @FXML
    private TextField rename_field;

    @FXML
    private TextField recountry_field;

    @FXML
    private ChoiceBox<String> rechoose_status_box;

    @FXML
    private Label error_edit_label;

    @FXML
    private Button edit_button;

    @FXML
    private Button delete_button;

    @FXML
    private Label error_delete_button;

    @FXML
    private Button exitButton;

    @FXML
    public void initialize(){
        AirportServiceImpl airportService = new AirportServiceImpl();
        ObservableList<Airport> airportsList = null;
        try{
            airportsList = FXCollections.observableArrayList(airportService.getAllAirport());
        }catch (Exception e){
            error_add_label.setText("Список пуст");
            e.printStackTrace();
        }
        AirportTableView.setItems(airportsList);
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        country_column.setCellValueFactory(new PropertyValueFactory<>("country"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("weatherStatus"));
        ViewAirports();
        WeatherStatus weatherStatus [] = WeatherStatus.values();
        List<String> statusList = Arrays.stream(weatherStatus).map(Enum::name).collect(Collectors.toList());
        choose_status_box.getItems().addAll(statusList);
        rechoose_status_box.getItems().addAll(statusList);
    }

    public void ViewAirports(){
        AirportServiceImpl airportService = new AirportServiceImpl();
        ObservableList<Airport> airportsList = FXCollections.observableArrayList(airportService.getAllAirport());
        AirportTableView.getItems().clear();
        AirportTableView.setItems(airportsList);
    }

    public void AddAirport(){
        AirportServiceImpl airportService = new AirportServiceImpl();
        Airport airport = new Airport();
        airport.setName(name_field.getText());
        airport.setCountry(country_field.getText());
        WeatherStatus weatherStatus = WeatherStatus.valueOf(choose_status_box.getValue());
        System.out.println(weatherStatus);
        airport.setWeatherStatus(weatherStatus);
        airportService.addAirport(airport);
        ViewAirports();
    }

    public void EditAirport(){};

    public void DeleteAirport(){};

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
