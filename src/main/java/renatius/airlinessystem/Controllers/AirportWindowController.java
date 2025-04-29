package renatius.airlinessystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import renatius.airlinessystem.Entity.GroundUnit.Airport;

import java.io.IOException;


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

    public void AddAirport(){};

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
