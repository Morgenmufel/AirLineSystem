package renatius.airlinessystem.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import renatius.airlinessystem.Entity.Crew.FlightCrew;
import renatius.airlinessystem.Entity.Enum.WeatherStatus;
import renatius.airlinessystem.Entity.GroundUnit.Airport;
import renatius.airlinessystem.services.impl.AirportServiceImpl;
import renatius.airlinessystem.services.impl.CrewServiceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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

    @FXML
    public void initialize(){
        AirportServiceImpl airportService = new AirportServiceImpl();
        ObservableList<Airport> airportsList = null;
        try{
            airportsList = FXCollections.observableArrayList(airportService.getAllAirport());
        }catch (Exception e){
            error_add_label.setText("Список пуст");
            e.printStackTrace();
        }
        AirportTableView.setItems(airportsList);
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        country_column.setCellValueFactory(new PropertyValueFactory<>("country"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("weatherStatus"));
        ViewAirports();
        WeatherStatus weatherStatus [] = WeatherStatus.values();
        List<String> statusList = Arrays.stream(weatherStatus).map(Enum::name).collect(Collectors.toList());
        choose_status_box.getItems().addAll(statusList);
        rechoose_status_box.getItems().addAll(statusList);
        //////////////////////////////////////////////////////
        name_column.setCellFactory(col -> {
            TableCell<Airport, String> cell = new TableCell<>() {
                private final Text text = new Text();

                {
                    text.wrappingWidthProperty().bind(col.widthProperty().subtract(10));
                    setGraphic(text);
                    setPrefHeight(Control.USE_COMPUTED_SIZE);
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        text.setText("");
                    } else {
                        text.setText(item);
                    }
                }
            };
            return cell;
        });
    }

    public void ViewAirports(){
        AirportServiceImpl airportService = new AirportServiceImpl();
        ObservableList<Airport> airportsList = FXCollections.observableArrayList(airportService.getAllAirport());
        AirportTableView.getItems().clear();
        AirportTableView.setItems(airportsList);
    }

    public void AddAirport(){
        AirportServiceImpl airportService = new AirportServiceImpl();
        Airport airport = new Airport();
        String name =name_field.getText();
        String country =country_field.getText();
        if(name.isEmpty() || country.isEmpty()){
            error_add_label.setText("Заполните все поля");
            return;
        }
        airport.setName(name);
        airport.setCountry(country);
        if(choose_status_box.getSelectionModel().isEmpty()){
            error_add_label.setText("Выберите погодные условия");
            return;
        }
        else {
            WeatherStatus weatherStatus = WeatherStatus.valueOf(choose_status_box.getValue());
        System.out.println(weatherStatus);
        airport.setWeatherStatus(weatherStatus);
        airportService.addAirport(airport);
        ViewAirports();
        }
    }

    public void EditAirport(){
        AirportServiceImpl airportService = new AirportServiceImpl();
        Airport airport = AirportTableView.getSelectionModel().getSelectedItem();
        if (airport == null){
            error_edit_label.setText("Выберите аэропорт");
            return;
        }
        if (rename_field.getText().isEmpty() && recountry_field.getText().isEmpty() && rechoose_status_box.getSelectionModel().isEmpty()){
            error_edit_label.setText("Измените хотя бы одно поле");
            return;
        }
        String name = rename_field.getText();
        String country = recountry_field.getText();
        if(rechoose_status_box.getValue() == null){
            error_edit_label.setText("Выберите погодные условия");
            return;
        }
        WeatherStatus weatherStatus = WeatherStatus.valueOf(rechoose_status_box.getValue());
        try {
            if(!name.isEmpty()){
                airport.setName(name);
            }
            if(!country.isEmpty()){
                airport.setCountry(country);
            }
            if(!rechoose_status_box.getSelectionModel().isEmpty()){
                airport.setWeatherStatus(weatherStatus);
            }
            airportService.updateAirport(airport);
            ViewAirports();
            error_edit_label.setText("Данные самолёта успешно изменёны!");
            rename_field.clear();
            recountry_field.clear();
        }catch (Exception e){
            error_edit_label.setText("Ошибка при обновлении: " + e.getMessage());
        }
        ViewAirports();
    };

    public void DeleteAirport(){
        AirportServiceImpl airportService = new AirportServiceImpl();
        Airport airport = AirportTableView.getSelectionModel().getSelectedItem();
        if (airport == null){
            error_delete_button.setText("Выберите аэропорт");
            return;
        }
        airportService.deleteAirport(airport);
        ViewAirports();
        error_delete_button.setText("Аэропорт успешно удалён");
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
