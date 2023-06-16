package ru.hse.vmoroke;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChangePasswordController {
    public PasswordField txtFieldPassword;
    public PasswordField txtFieldPasswordRepeat;
    private Person currentUser;

    public void setCurrentUser(Person p) {
        currentUser = p;
    }

    public void changePasswordClick() throws IOException {
        String password = txtFieldPassword.getText();
        String repassword = txtFieldPassword.getText();

        if (!Util.checkPassword(password)) {
            Util.showAlert("Проверка пароля", "Пароль не соответсвует требованиям");
            return;
        }

        if (!password.equals(repassword)) {
            Util.showAlert("Проверка пароля", "Пароли не совпадают");
            return;
        }

        if (Objects.equals(Util.hashPassword(password), currentUser.getPassword())) {
            Util.showAlert("Проверка пароля", "Пароль совпадает с предыдущим");
            return;
        }

        currentUser.setPassword(Util.hashPassword(password));
        PersonRepository.getInstance().save();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setScene(scene);
    }
}
