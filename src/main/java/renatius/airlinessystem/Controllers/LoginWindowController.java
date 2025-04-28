package renatius.airlinessystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginWindowController {

    @FXML
    private TextField login_field;

    @FXML
    private TextField password_field;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label absence_label;


    public void initialization(){
        String current_login = login_field.getText().trim();
        String current_password = password_field.getText().trim();
        if (!current_login.equals("") && !current_password.equals("")) {
            LogIn(current_login, current_password);
        } else {
            absence_label.setText("Неправильный ввод логина или пароля!");
        }
    }

    public void LogIn(String current_login, String current_password) {
        if(AccountExistInFile(current_login,current_password)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ActionSelection.fxml"));
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
        }
        else{
            absence_label.setText("Учётная запись не найдена");
        }
    }

    public void logout(){
        Stage stage=(Stage) exitButton.getScene().getWindow();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Выход");
        alert.setHeaderText("Вы собираетесь выйти из системы!");
        alert.setContentText("Вы действительно хотите выйти из программы?:");
        if(alert.showAndWait().get()== ButtonType.OK){
            stage.close();
        }
    }

    public boolean AccountExistInFile(String current_login, String current_password){
        List<String> login_and_password=new ArrayList<>();
        try {
            BufferedReader reader=new BufferedReader(new FileReader("account.txt"));
            String line;
            while((line=reader.readLine())!=null){
                if(!line.trim().isEmpty()){
                    login_and_password.add(line.trim());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < login_and_password.size()-1; i++) {
            if(current_login.equals(login_and_password.get(i++))&&current_password.equals(login_and_password.get(i++))) return true;
        }
        return false;
    }
}
