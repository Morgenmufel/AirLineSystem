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

import java.io.IOException;


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

    public void AddFlight(){};


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
