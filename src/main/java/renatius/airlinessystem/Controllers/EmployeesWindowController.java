package renatius.airlinessystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import renatius.airlinessystem.Entity.Crew.FlightCrew;

import java.io.IOException;

public class EmployeesWindowController {

    @FXML
    private TableColumn<FlightCrew,String> full_name_column;

    @FXML
    private TableColumn<FlightCrew,String> post_column;

    @FXML
    private TableColumn<FlightCrew,String> status_column;

    @FXML
    private Button view_button;

    @FXML
    private TextField surname_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField patronymic_field;

    @FXML
    private Label error_add_label;

    @FXML
    private Button add_employee_button;

    @FXML
    private Button delete_employee_button;

    @FXML
    private ChoiceBox<String> choose_position_box;

    @FXML
    private Label error_delete_label;

    @FXML
    private TextField age_field_field;

    @FXML
    private RadioButton man_rbutton;

    @FXML
    private RadioButton woman_rbutton;

    @FXML
    private Button exitButton;

    public void ViewEmployees(){};

    public void AddNewEmployee(){};

    public void DeleteEmployee(){};

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
