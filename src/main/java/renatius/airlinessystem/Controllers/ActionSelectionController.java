package renatius.airlinessystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ActionSelectionController {
    @FXML
    private Button employees_button;

    @FXML
    private Button flights_button;

    @FXML
    private Button airplane_button;

    @FXML
    private Button airports_button;

    @FXML
    private Button exitButton;

    public void loadSceneWithWorkingOnEmployees() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/EmployeesWindow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exitButton.getScene().getWindow().hide();
        Parent root=loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Челики");
        stage.setResizable(false);
        root.requestFocus();
        stage.show();

    }

    public void loadSceneWithWorkingOnFlights() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/FlightWindow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exitButton.getScene().getWindow().hide();
        Parent root=loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Рейсики");
        stage.setResizable(false);
        root.requestFocus();
        stage.show();
    }
    public void loadSceneWithWorkingOnAirplane(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AirplaneWindow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exitButton.getScene().getWindow().hide();
        Parent root=loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Челики");
        stage.setResizable(false);
        root.requestFocus();
        stage.show();
    }

    public void loadSceneWithWorkingOnAirports() {

    }
    public void logout(){
        exitButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/LoginWindow.fxml"));
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
