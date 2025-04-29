package renatius.airlinessystem.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import renatius.airlinessystem.Entity.Crew.*;
import renatius.airlinessystem.services.impl.CrewServiceImpl;

import java.io.IOException;
import java.util.ArrayList;

public class EmployeesWindowController {

    @FXML
    private TableView<FlightCrew> employeeTableView;

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

    @FXML
    public void initialize() {
        CrewServiceImpl crewService = new CrewServiceImpl();
        ObservableList< FlightCrew> flightCrews = null;
        try{
         flightCrews = FXCollections.observableArrayList(crewService.getAllCrew());
        }catch (Exception e){
            System.out.println("Список пуст");
            e.printStackTrace();
        }
        employeeTableView.setItems(flightCrews);
        full_name_column.setCellValueFactory(new PropertyValueFactory<>("crewName"));
        post_column.setCellValueFactory(new PropertyValueFactory<>("post"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("status"));
        ArrayList<String> flightCrews1 = new ArrayList<String>(){{
            add("AirborneSensorOperator");
            add("Captain");
            add("FirstOfficer");
            add("FlightAttendant");
            add("FlightEngineer");
            add("FlightMedic");
            add("Purser");
            add("ReliefCrew");
            add("SecondOfficer");
            add("ThirdOfficer");
        }};
        choose_position_box.getItems().addAll(flightCrews1);
        String s = choose_position_box.getValue();
    }


    public void ViewEmployees(){
        CrewServiceImpl crewService = new CrewServiceImpl();
        ObservableList<FlightCrew> crews = FXCollections.observableArrayList(crewService.getAllCrew());
        employeeTableView.getItems().clear();
        employeeTableView.setItems(crews);
    };

    public void AddNewEmployee(){
        FlightCrew flightCrew;
        switch (choose_position_box.getValue()){
            case "AirborneSensorOperator":
                flightCrew = new AirborneSensorOperator();
                flightCrew.setPost("AirborneSensorOperator");
                break;
            case "Captain":
                flightCrew = new Captain();
                flightCrew.setPost("Captain");
                break;
            case "FirstOfficer":
                flightCrew = new FirstOfficer();
                flightCrew.setPost("FirstOfficer");
                break;
            case "FlightAttendant":
                flightCrew = new FlightAttendant();
                flightCrew.setPost("FlightAttendant");
                break;
            case "FlightEngineer":
                flightCrew = new FlightEngineer();
                flightCrew.setPost("FlightEngineer");
                break;
            case "FlightMedic":
                flightCrew = new FlightMedic();
                flightCrew.setPost("FlightMedic");
                break;
            case "Purser":
                flightCrew = new Purser();
                flightCrew.setPost("Purser");
                break;
            case "ReliefCrew":
                flightCrew = new ReliefCrew();
                flightCrew.setPost("ReliefCrew");
                break;
            case "SecondOfficer":
                flightCrew = new SecondOfficer();
                flightCrew.setPost("SecondOfficer");
                break;
            default: case "ThirdOfficer":
                flightCrew = new ThirdOfficer();
                flightCrew.setPost("ThirdOfficer");
                break;
        }
        CrewServiceImpl crewService = new CrewServiceImpl();
        if(checkEmptyString(name_field.getText().trim()) && checkEmptyString(surname_field.getText().trim()) && checkEmptyString(patronymic_field.getText().trim())){
            flightCrew.setCrewName(surname_field.getText().trim() + " " + name_field.getText().trim() + " " + patronymic_field.getText().trim());
        }else {
            error_add_label.setText("Заполните ФИО");
            return;
        }
        if(checkEmptyString(age_field_field.getText().trim())){
            flightCrew.setAge(Integer.parseInt(age_field_field.getText().trim()));
        }else {error_add_label.setText("Заполните возраст");}
        flightCrew.setStatus("FREE");
        if (man_rbutton.isSelected()){
            flightCrew.setSex("Мужчина");
        }else if (woman_rbutton.isSelected()){
            flightCrew.setSex("Женщина");
        }
        else {
            error_add_label.setText("Выберите пол сотрудника");
            return;
        }
        crewService.addCrew(flightCrew);
        ViewEmployees();
    };

    public void DeleteEmployee() {
        CrewServiceImpl crewService = new CrewServiceImpl();
        FlightCrew flightCrew = employeeTableView.getSelectionModel().getSelectedItem();
        if (flightCrew == null){
            error_delete_label.setText("Выберите пользователя");
            return;
        }
        crewService.deleteCrew(flightCrew);
        ViewEmployees();
    }

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

    public boolean checkEmptyString(String s){
        if (s.isEmpty() || s.equals(null)){
            return false;
        }else return true;
    }
}
