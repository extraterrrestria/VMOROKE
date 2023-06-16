package ru.hse.vmoroke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestUserController implements Initializable {
    public TextField firstName;
    public TextField lastName;
    public TextField middleName;
    public DatePicker birthday;
    public TextField email;
    public ComboBox<String> secretQuestion;
    public TextField answer;

    private Person currentUser;

    public void setCurrentUser(Person p) {
        currentUser = p;
    }

    boolean checkEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public void sendRequestClick() throws IOException {
        if (checkEmpty(firstName.getText())) {
            Util.showAlert("Проверка имени", "Введите имя");
            return;
        }

        if (checkEmpty(lastName.getText())) {
            Util.showAlert("Проверка фамилии", "Введите фамилию");
            return;
        }

        if (checkEmpty(middleName.getText())) {
            Util.showAlert("Проверка отчества", "Введите отчество");
            return;
        }

        if (checkEmpty(email.getText())) {
            Util.showAlert("Проверка email", "Введите email");
            return;
        }

        if (birthday.getValue() == null) {
            Util.showAlert("Проверка даты рождения", "Введите дату рождения");
            return;
        }

        if (secretQuestion.getSelectionModel().getSelectedIndex() == -1) {
            Util.showAlert("Проверка выбора вопроса", "Выберите вопрос");
            return;
        }

        if (checkEmpty(answer.getText())) {
            Util.showAlert("Проверка ответа", "Введите ответ");
            return;
        }

        int countMatch = 0;
        if (currentUser.getFirstName().equals(firstName.getText()))
            countMatch++;
        if (currentUser.getLastName().equals(lastName.getText()))
            countMatch++;
        if (currentUser.getMiddleName().equals(middleName.getText()))
            countMatch++;
        if (currentUser.getAnswer().equals(answer.getText()))
            countMatch++;
        if (currentUser.getEmail().equals(email.getText()))
            countMatch++;
        if (currentUser.getBirthday().equals(birthday.getValue()))
            countMatch++;
        if (currentUser.getSecretQuestion().equals(secretQuestion.getValue()))
            countMatch++;

        double p = 100.0 * countMatch / 7;

        Request request = new Request(currentUser.getLogin(), firstName.getText(), lastName.getText(), middleName.getText(),
                birthday.getValue(), email.getText(), secretQuestion.getValue(), answer.getText(), (int)p);
        PersonRepository.getInstance().saveRequest(request);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> questions = FXCollections.observableArrayList("Девичья фамилия матери",
                "Любимое блюдо", "Любимая книга");
        secretQuestion.setItems(questions);
    }
}
