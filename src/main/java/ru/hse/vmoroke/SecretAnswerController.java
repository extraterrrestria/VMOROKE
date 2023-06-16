package ru.hse.vmoroke;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SecretAnswerController {
    public TextField txtFieldSecretQuestion;
    public TextField txtFieldAnswer;

    private Person currentUser;

    @FXML
    public void repairPasswordClick() throws IOException {
        String answer = txtFieldAnswer.getText();
        if (answer.equals(currentUser.getAnswer())) {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("changepass.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ChangePasswordController controller = fxmlLoader.getController();
            controller.setCurrentUser(currentUser);
            Stage stage = App.mainStage;
            stage.setScene(scene);
        }
        else {
            currentUser.setBlocked(true);
            PersonRepository.getInstance().save();
            Util.showAlert("Блокировка", "Ответ неверный");
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("request_user_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            RequestUserController controller = fxmlLoader.getController();
            controller.setCurrentUser(currentUser);
            Stage stage = App.mainStage;
            stage.setScene(scene);
        }
    }

    public void setCurrentUser(Person p) {
        currentUser = p;

        txtFieldSecretQuestion.setText(currentUser.getSecretQuestion());
    }
}
