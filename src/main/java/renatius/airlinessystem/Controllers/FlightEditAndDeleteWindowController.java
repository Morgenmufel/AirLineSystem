package renatius.airlinessystem.Controllers;

import jakarta.transaction.Transactional;
import javafx.beans.property.SimpleStringProperty;
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
import org.hibernate.Session;
import org.hibernate.Transaction;
import renatius.airlinessystem.Entity.AbstractEntity.Flight;
import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;
import renatius.airlinessystem.Entity.Crew.FlightCrew;
import renatius.airlinessystem.Entity.GroundUnit.Airport;
import renatius.airlinessystem.Hibernate.HibernateUtil;
import renatius.airlinessystem.services.impl.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class FlightEditAndDeleteWindowController {

    @FXML
    private TableView<Flight> EditTableView;

    @FXML
    private TableColumn<Flight, String> departure_datetime_column;

    @FXML
    private TableColumn<Flight, String> arrival_date_time_column;

    @FXML
    private TableColumn<Flight, String> airplane_column;

    @FXML
    private TableColumn<Flight, String> arrival_airport_column;

    @FXML
    private TableColumn<Flight, String> departure_airport_column;

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

    @FXML
    public void initialize() {
        FlightServiceImpl service = new FlightServiceImpl();
        ObservableList<Flight> flights = FXCollections.observableArrayList(service.getAllFlights());
        EditTableView.getItems().setAll(flights);
        departure_datetime_column.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        arrival_date_time_column.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        airplane_column.setCellValueFactory(cellData -> {
            AirPlane airPlane = cellData.getValue().getAirPlane();
            return new SimpleStringProperty(airPlane != null ? airPlane.getPlaneName() : "Не указан");
        });
        arrival_airport_column.setCellValueFactory(new PropertyValueFactory<>("fromAirport"));
        departure_airport_column.setCellValueFactory(new PropertyValueFactory<>("toAirport"));
        AirportServiceImpl airportService = new AirportServiceImpl();
        AirPlaneServiceImpl airPlaneService = new AirPlaneServiceImpl();
        List<AirPlane> airPlanes = airPlaneService.findByStatus();
        List<Airport> airports = airportService.getAllAirport();
        airPlanes.forEach((airPlane) ->{
            rechoose_airplane_box.getItems().add(airPlane.getPlaneName());
        });
        airports.forEach((airport) ->{
            redeparture_airport_box.getItems().add(airport.getName());
            rearrival_airport_box.getItems().add(airport.getName());
        });

        arrival_airport_column.setCellFactory(col -> {
            TableCell<Flight, String> cell = new TableCell<>() {
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
    @Transactional
    public void EditFlight(){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Flight selectedFlight = EditTableView.getSelectionModel().getSelectedItem();
            if (selectedFlight == null) {
                error_add_label.setText("Выберите рейс");
                return;
            }

            Flight flight = session.get(Flight.class, selectedFlight.getId());
            if (flight == null) {
                error_add_label.setText("Рейс не найден");
                return;
            }

            LocalDateTime departureTime = parseDateTime(date_field.getText(), time_field.getText());
            String airPlaneName = rechoose_airplane_box.getSelectionModel().getSelectedItem();
            String fromAirport = redeparture_airport_box.getSelectionModel().getSelectedItem();
            String toAirport = rearrival_airport_box.getSelectionModel().getSelectedItem();

            if (fromAirport == null || toAirport == null || airPlaneName == null) {
                error_add_label.setText("Заполните все поля");
                return;
            }

            if (flight.getAirPlane() == null  || !airPlaneName.equals(flight.getAirPlane().getPlaneName())) {
                AirPlane newAirPlane = session.createQuery(
                                "FROM AirPlane WHERE planeName = :name", AirPlane.class)
                        .setParameter("name", airPlaneName)
                        .uniqueResult();

                if (newAirPlane == null) {
                    error_add_label.setText("Самолет не найден");
                    return;
                }

                if (flight.getAirPlane() != null) {
                    flight.getAirPlane().setFlight(null);
                    flight.getAirPlane().setAirPlaneStatus("FREE");
                    session.update(flight.getAirPlane());
                }

                newAirPlane.setFlight(flight);
                newAirPlane.setAirPlaneStatus("ASSIGNED");
                flight.setAirPlane(newAirPlane);
                session.update(newAirPlane);
            }

            flight.setFromAirport(fromAirport);
            flight.setToAirport(toAirport);
            flight.setDepartureTime(departureTime);

            session.update(flight);
            transaction.commit();


        } catch (DateTimeParseException e) {
            e.printStackTrace();
            rollbackTransaction(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction(transaction);
        } finally {
            closeSession(session);
        }
    }

    public void DeleteFlight(){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Flight flight = session.createQuery(
                            "SELECT f FROM Flight f LEFT JOIN FETCH f.airPlane LEFT JOIN FETCH f.flightCrewList WHERE f.id = :id",
                            Flight.class)
                    .setParameter("id", EditTableView.getSelectionModel().getSelectedItem().getId())
                    .uniqueResult();

            if (flight == null) {
                error_add_label.setText("Рейс не найден");
                return;
            }

            if (flight.getAirPlane() != null) {
                AirPlane airPlane = flight.getAirPlane();
                airPlane.setFlight(null);  // Разрываем связь
                airPlane.setAirPlaneStatus("FREE");
                session.update(airPlane);
            }

            for (FlightCrew crew : flight.getFlightCrewList()) {
                crew.setFlight(null);
                crew.setStatus("FREE");
                session.update(crew);
            }

            flight.getFlightCrewList().clear();

            session.delete(flight);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
                    error_add_label.setText("Ошибка удаления: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    private void ViewFlights() {

        FlightServiceImpl service = new FlightServiceImpl();
        ObservableList<Flight> flights = FXCollections.observableArrayList(service.getAllFlights());

        EditTableView.getItems().setAll(flights);
        departure_datetime_column.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        arrival_date_time_column.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        airplane_column.setCellValueFactory(cellData -> {
            AirPlane airPlane = cellData.getValue().getAirPlane();
            return new SimpleStringProperty(airPlane != null ? airPlane.getPlaneName() : "Не указан");
        });
        arrival_airport_column.setCellValueFactory(new PropertyValueFactory<>("fromAirport"));
        departure_airport_column.setCellValueFactory(new PropertyValueFactory<>("toAirport"));
        AirportServiceImpl airportService = new AirportServiceImpl();
        AirPlaneServiceImpl airPlaneService = new AirPlaneServiceImpl();
        List<AirPlane> airPlanes = airPlaneService.findByStatus();
        List<Airport> airports = airportService.getAllAirport();
        airPlanes.forEach((airPlane) ->{
            rechoose_airplane_box.getItems().add(airPlane.getPlaneName());
        });
        airports.forEach((airport) ->{
            redeparture_airport_box.getItems().add(airport.getName());
            rearrival_airport_box.getItems().add(airport.getName());
        });

    }


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
    @Transactional
    public void updateFlightWithRelations() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Flight selectedFlight = EditTableView.getSelectionModel().getSelectedItem();
            if (selectedFlight == null) {
                error_add_label.setText("Выберите рейс");
                return;
            }

            Flight flight = session.get(Flight.class, selectedFlight.getId());
            if (flight == null) {
                error_add_label.setText("Рейс не найден");
                return;
            }

            LocalDateTime departureTime = parseDateTime(date_field.getText(), time_field.getText());
            String airPlaneName = rechoose_airplane_box.getSelectionModel().getSelectedItem();
            String fromAirport = redeparture_airport_box.getSelectionModel().getSelectedItem();
            String toAirport = rearrival_airport_box.getSelectionModel().getSelectedItem();

            if (fromAirport == null || toAirport == null || airPlaneName == null) {
                error_add_label.setText("Заполните все поля");
                return;
            }

            if (!airPlaneName.equals(flight.getAirPlane().getPlaneName())) {
                AirPlane newAirPlane = session.createQuery(
                                "FROM AirPlane WHERE planeName = :name", AirPlane.class)
                        .setParameter("name", airPlaneName)
                        .uniqueResult();

                if (newAirPlane == null) {
                    error_add_label.setText("Самолет не найден");
                    return;
                }

                if (flight.getAirPlane() != null) {
                    flight.getAirPlane().setFlight(null);
                    flight.getAirPlane().setAirPlaneStatus("FREE");
                    session.update(flight.getAirPlane());
                }

                newAirPlane.setFlight(flight);
                newAirPlane.setAirPlaneStatus("ASSIGNED");
                flight.setAirPlane(newAirPlane);
                session.update(newAirPlane);
            }

            flight.setFromAirport(fromAirport);
            flight.setToAirport(toAirport);
            flight.setDepartureTime(departureTime);

            session.update(flight);
            transaction.commit();


        } catch (DateTimeParseException e) {
            rollbackTransaction(transaction);
        } catch (Exception e) {
            rollbackTransaction(transaction);
        } finally {
            closeSession(session);
        }
    }

    private LocalDateTime parseDateTime(String dateStr, String timeStr) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));
        return LocalDateTime.of(date, time);
    }

    private void rollbackTransaction(Transaction transaction) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
    }

    private void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
