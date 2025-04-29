package renatius.airlinessystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

import java.io.IOException;


public class AirLinesSystemApplication extends Application {

    public static void main(String[] args) throws ClassNotFoundException {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/LoginWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Оформление рейсов");
        stage.setMinWidth(850);
        stage.setMinHeight(630);
        stage.setResizable (false);
        root.requestFocus();
        stage.show();
    }

}
