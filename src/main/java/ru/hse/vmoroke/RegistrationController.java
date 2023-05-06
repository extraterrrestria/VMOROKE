package ru.hse.vmoroke;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController  implements Initializable {


    @FXML
    private ComboBox<String> userRole;
    @FXML
    private Label exc_message;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> langs = FXCollections.observableArrayList("Пользователь", "Аналитик", "Администратор");
        userRole.setItems(langs);
    }
    @FXML
    void onRegister(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setScene(scene);

    }

}