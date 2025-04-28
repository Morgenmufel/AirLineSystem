package renatius.airlinessystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;

import java.io.IOException;

public class AirplaneWindowController {

    @FXML
    private TableColumn<AirPlane,String> name_column;

    @FXML
    private TableColumn<AirPlane,String> model_column;

    @FXML
    private TableColumn<AirPlane,String> status_column;

    @FXML
    private TableColumn<AirPlane,String> flight_column;

    @FXML
    private Button view_button;

    @FXML
    private TextField name_field;

    @FXML
    private TextField model_field;

    @FXML
    private Label error_add_label;

    @FXML
    private Button add_button;

    @FXML
    private TextField rename_field;

    @FXML
    private TextField remodel_field;

    @FXML
    private Label error_edit_label;

    @FXML
    private Button edit_button;

    @FXML
    private Button delete_button;

    @FXML
    private Label error_delete_label;

    @FXML
    private Button exitButton;

    public void ViewAirplanes(){};

    public void AddAirplane(){};

    public void EditAirplane(){};

    public void DeleteAirplane(){};

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
