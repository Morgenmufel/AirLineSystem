package renatius.airlinessystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FlightWindowController {

    @FXML
    private Button add_flight_button;

    @FXML
    private Button edit_flight_button;

    @FXML
    private Button delete_flight_button;

    @FXML
    private Button exitButton;

    public void loadSceneWithAddingFlight(){};

    public void loadSceneWithEditingFlight(){};

    public void loadSceneWithDeletingFlight(){};

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
