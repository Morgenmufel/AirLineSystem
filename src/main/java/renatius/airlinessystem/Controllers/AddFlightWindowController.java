package renatius.airlinessystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import renatius.airlinessystem.Entity.AbstractEntity.Flight;
import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;
import renatius.airlinessystem.Entity.GroundUnit.Airport;
import renatius.airlinessystem.services.impl.AirPlaneServiceImpl;
import renatius.airlinessystem.services.impl.AirportServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;
import java.util.function.Consumer;

public class AddFlightWindowController {

    @FXML
    private TextField date_field;

    @FXML
    private TextField time_field;

    @FXML
    private ChoiceBox<String> choose_airplane_box;

    @FXML
    private ChoiceBox<String> departure_airport_box;

    @FXML
    private ChoiceBox<String> arrival_airport_box;

    @FXML
    private Label error_add_label;

    @FXML
    private Button add_button;

    @FXML
    private Button exitButton;

    @FXML
    public void initialize() {
        AirportServiceImpl airportService = new AirportServiceImpl();
        AirPlaneServiceImpl airPlaneService = new AirPlaneServiceImpl();
        List<AirPlane> airPlanes = airPlaneService.findByStatus();
        List<Airport> airports = airportService.getAllAirport();
        airPlanes.forEach((airPlane) ->{
            choose_airplane_box.getItems().add(airPlane.getPlaneName());
        });
        airports.forEach((airport) ->{
           departure_airport_box.getItems().add(airport.getName());
           arrival_airport_box.getItems().add(airport.getName());
        });
    }

    public void AddFlight(){
        Flight flight = new Flight();
        AirportServiceImpl airportService = new AirportServiceImpl();
        AirPlaneServiceImpl airPlaneService = new AirPlaneServiceImpl();
        LocalDate arriveDate = LocalDate.parse(date_field.getText());
        LocalTime arriveTime = LocalTime.parse(time_field.getText());
        String airPlaneName = choose_airplane_box.getSelectionModel().getSelectedItem();
        String whereAirport = departure_airport_box.getSelectionModel().getSelectedItem();
        String toAirport = arrival_airport_box.getSelectionModel().getSelectedItem();
        if(whereAirport == null || whereAirport.isEmpty()){
            error_add_label.setText("Please select departure airport");
            return;
        }
        if(toAirport == null || toAirport.isEmpty()){
            error_add_label.setText("Please select arrival airport");
            return;
        }
        if(airPlaneName.isEmpty() || airPlaneName == null){
            error_add_label.setText("Please select airplane");
            return;
        }
        if (arriveDate.equals("") || arriveDate.equals(null)){
            error_add_label.setText("Please enter flight date");
            return;
        }
        if(arriveTime.equals("") || arriveTime.equals(null)){
            error_add_label.setText("Please enter flight time");
            return;
        }
        AirPlane airPlane = airPlaneService.findByName(airPlaneName);

    };


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
}
