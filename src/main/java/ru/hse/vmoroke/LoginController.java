package ru.hse.vmoroke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {


    @FXML
    private PasswordField password;
    @FXML
    private TextField login;
    @FXML
    private Button logback;

    @FXML
    void test(ActionEvent event) {
        Stage stage = App.mainStage;
        stage.setTitle(login.getText());

    }
    @FXML
    void onRegisterClick(ActionEvent event) throws IOException  {
        System.out.println("reg");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setScene(scene);
    }
    @FXML
    protected void onLoginClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("user_profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setTitle(login.getText());
        stage.setScene(scene);
    }
    @FXML
    void onLogBackClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("base_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setScene(scene);
    }

}