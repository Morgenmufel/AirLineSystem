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
    private Button view_flight_button;

    @FXML
    private Button add_flight_button;

    @FXML
    private Button edit_flight_button;

    @FXML
    private Button exitButton;

    public void loadSceneWithViewingFlight (){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ViewFlightWindow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exitButton.getScene().getWindow().hide();
        Parent root=loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Авиарейсы");
        stage.setResizable(false);
        root.requestFocus();
        stage.show();
    };

    public void loadSceneWithAddingFlight(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AddFlightWindow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exitButton.getScene().getWindow().hide();
        Parent root=loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Авиарейсы");
        stage.setResizable(false);
        root.requestFocus();
        stage.show();
    };

    public void loadSceneWithEditingAndDeletingFlight(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/FlightEditAndDeleteWindow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exitButton.getScene().getWindow().hide();
        Parent root=loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Авиарейсы");
        stage.setResizable(false);
        root.requestFocus();
        stage.show();
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
