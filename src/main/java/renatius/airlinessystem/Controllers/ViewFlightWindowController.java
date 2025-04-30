package renatius.airlinessystem.Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import renatius.airlinessystem.Entity.AbstractEntity.Flight;
import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;
import renatius.airlinessystem.Entity.Crew.FlightCrew;
import renatius.airlinessystem.services.impl.FlightServiceImpl;

import java.io.IOException;
import java.nio.Buffer;
import java.util.*;
public class ViewFlightWindowController {

    @FXML
    private TableView<Flight> AirportTableView;

    @FXML
    private TableColumn<Flight,String> departures_date_column;

    @FXML
    private TableColumn<Flight,String> arrival_time_column;

    @FXML
    private TableColumn<Flight,String> airplane_column;

    @FXML
    private TableColumn<Flight,String> employees_column;

    @FXML
    private TableColumn<Flight,String> from_airport_column;

    @FXML
    private TableColumn<Flight,String> to_airport_column;

    @FXML
    private Button exitButton;

    @FXML
    public void initialize() {
        FlightServiceImpl service = new FlightServiceImpl();
        ObservableList<Flight> flights = FXCollections.observableArrayList(service.getAllFlights());

        AirportTableView.getItems().setAll(flights);
        departures_date_column.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        arrival_time_column.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        airplane_column.setCellValueFactory(cellData -> {
            AirPlane airPlane = cellData.getValue().getAirPlane();
            return new SimpleStringProperty(airPlane != null ? airPlane.getPlaneName() : "Не указан");
        });
        employees_column.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAllNamesOfCrew()));
        from_airport_column.setCellValueFactory(new PropertyValueFactory<>("fromAirport"));
        to_airport_column.setCellValueFactory(new PropertyValueFactory<>("toAirport"));
    }

    public void logout(){
        exitButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/FlightWindow.fxml"));
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

    public String getAllNamesOfCrew(List<FlightCrew> crewList){
        String s = "";
        for(FlightCrew flightCrew : crewList){
            s += flightCrew.getCrewName() + ", ";
        }
        return s;
    }




}
