package ru.hse.vmoroke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    protected void onLoginButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setTitle(password.getText());
        stage.setScene(scene);
    }
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

}