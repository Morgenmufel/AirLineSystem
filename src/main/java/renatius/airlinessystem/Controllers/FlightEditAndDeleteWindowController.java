package renatius.airlinessystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import renatius.airlinessystem.Entity.AbstractEntity.Flight;

import java.io.IOException;

public class FlightEditAndDeleteWindowController {

    @FXML
    private TableView<Flight> EditTableView;

    @FXML
    private TableColumn<Flight, Integer> departure_datetime_column;

    @FXML
    private TableColumn<Flight, Integer> arrival_date_time_column;

    @FXML
    private TableColumn<Flight, Integer> airplane_column;

    @FXML
    private TableColumn<Flight, Integer> arrival_airport_column;

    @FXML
    private TableColumn<Flight, Integer> departure_airport_column;

    @FXML
    private TextField date_field;

    @FXML
    private TextField time_field;

    @FXML
    private ChoiceBox<String> rechoose_airplane_box;

    @FXML
    private ChoiceBox<String> redeparture_airport_box;

    @FXML
    private ChoiceBox<String> rearrival_airport_box;

    @FXML
    private Label error_add_label;

    @FXML
    private Button add_button;

    @FXML
    private Button delete_button;

    @FXML
    private Button edit_button;

    @FXML
    private Button exitButton;

    public void EditFlight(){};

    public void DeleteFlight(){};

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
