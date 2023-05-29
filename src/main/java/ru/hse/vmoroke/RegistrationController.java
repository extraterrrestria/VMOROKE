package ru.hse.vmoroke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {


    public DatePicker birthday;
    public TextField email;
    public Button finishReg;
    public CheckBox checkBoxAgree;
    @FXML
    private ComboBox<String> secretQuestion;
    public TextField answer;
    @FXML
    private ComboBox<String> userRole;
    @FXML
    private Label exc_message;
    @FXML
    private Button regback;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repassword;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField middleName;

    private Person p;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (p == null)
            p = new Person();

        if (userRole != null) {
            ObservableList<String> langs = FXCollections.observableArrayList("Пользователь", "Аналитик", "Администратор");
            userRole.setItems(langs);
        }
        if (secretQuestion != null) {
            ObservableList<String> questions = FXCollections.observableArrayList("Девичья фамилия матери",
                    "Любимое блюдо", "Любимая книга");
            secretQuestion.setItems(questions);
        }
    }

    public void showPerson() {
        if (username != null) {
            username.setText(p.getLogin());
            password.setText(p.getPassword());
            repassword.setText(p.getPassword());
            userRole.setValue(p.getRole());
        }
        if (firstName != null) {
            firstName.setText(p.getFirstName());
            lastName.setText(p.getLastName());
            middleName.setText(p.getMiddleName());
            birthday.setValue(p.getBirthday());
            email.setText(p.getEmail());
        }
        if (secretQuestion != null) {
            secretQuestion.setValue(p.getSecretQuestion());
            answer.setText(p.getAnswer());
        }
    }

    public void init(Person p) {
        this.p = p;

        showPerson();
    }

    private boolean checkUnique(String login) {
        return true;
    }

    private boolean checkPassword(String password) {
        if (password.length() < 8)
            return false;

        boolean checkLower = false;
        boolean checkUpper = false;
        boolean checkDigit = false;
        boolean checkSpec = false;
        String specChars = "#$%^&*@!?";

        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                checkUpper = true;
            }
            if (Character.isLowerCase(password.charAt(i))) {
                checkLower = true;
            }
            if (Character.isDigit(password.charAt(i))) {
                checkDigit = true;
            }
            if (specChars.contains(password.charAt(i) + "")) {
                checkSpec = true;
            }
        }

        return checkLower && checkUpper && checkDigit && checkSpec;
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(content);
        alert.showAndWait();
    }


    @FXML
    void onRegister(ActionEvent event) throws IOException {
        String login = username.getText();
        String passwordValue = password.getText();
        String repasswordValue = repassword.getText();
        String role = userRole.getValue();

        if (!checkUnique(login)) {
            showAlert("", "Такой логин уже занят");
            return;
        }

        if (!checkPassword(passwordValue)) {
            showAlert("Проверка пароля", "Пароль не соответсвует требованиям");
            return;
        }

        if (!passwordValue.equals(repasswordValue)) {
            showAlert("Проверка пароля", "Пароли не совпадают");
            return;
        }

        if (checkEmpty(login)) {
            showAlert("Проверка логина", "Введите логин");
            return;
        }

        if (userRole.getSelectionModel().getSelectedIndex() == -1) {
            showAlert("Проверка роли", "Выберите роль");
            return;
        }

        // Сохранение логина и пароля
        RegistrationDataSaver.saveRegistrationData(login, passwordValue);

        p.setLogin(login);
        p.setPassword(passwordValue);
        p.setRole(role);

        showView("reg2.fxml");
    }

    void showView(String fxmlName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlName));
        Parent root = fxmlLoader.load();
        RegistrationController controller = fxmlLoader.getController();
        controller.init(p);
        Scene scene = new Scene(root);
        Stage stage = App.mainStage;
        stage.setScene(scene);
    }

    void saveStep2() {
        String _firstName = firstName.getText();
        String _lastName = lastName.getText();
        String _middleName = middleName.getText();
        LocalDate _birthday = birthday.getValue();
        String _email = email.getText();

        p.setFirstName(_firstName);
        p.setLastName(_lastName);
        p.setMiddleName(_middleName);
        p.setBirthday(_birthday);
        p.setEmail(_email);
    }

    void saveStep3() {
        String question = secretQuestion.getValue();
        String _answer = answer.getText();

        p.setSecretQuestion(question);
        p.setAnswer(_answer);
    }

    boolean checkEmpty(String s) {
        return s == null || s.isEmpty();
    }

    @FXML
    void onRegisterStep2(ActionEvent event) throws IOException {
        if (checkEmpty(firstName.getText())) {
            showAlert("Проверка имени", "Введите имя");
            return;
        }

        if (checkEmpty(lastName.getText())) {
            showAlert("Проверка фамилии", "Введите фамилию");
            return;
        }

        if (checkEmpty(middleName.getText())) {
            showAlert("Проверка отчества", "Введите отчество");
            return;
        }

        if (checkEmpty(email.getText())) {
            showAlert("Проверка email", "Введите email");
            return;
        }

        if (birthday.getValue() == null) {
            showAlert("Проверка даты рождения", "Введите дату рождения");
            return;
        }

        saveStep2();

        showView("reg3.fxml");
    }

    public void onRegisterStep3(ActionEvent actionEvent) throws IOException {
        if (secretQuestion.getSelectionModel().getSelectedIndex() == -1) {
            showAlert("Проверка выбора вопроса", "Выберите вопрос");
            return;
        }

        if (checkEmpty(answer.getText())) {
            showAlert("Проверка ответа", "Введите ответ");
            return;
        }

        saveStep3();
        try (PrintWriter writer_data = new PrintWriter(new FileWriter(FilesDirectory.getFileName() + "Login_Data.txt", true))) {
            writer_data.println(p.getLogin().toString() + " " + p.getRole().toString() + " " + p.getLastName().toString() + " " + p.getFirstName().toString() + " " + p.getMiddleName().toString() +  " " + p.getBirthday().toString() + " " + p.getEmail().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setScene(scene);
    }

    public void backStep2(ActionEvent actionEvent) throws IOException {
        saveStep2();

        showView("registration.fxml");
    }

    public void backStep3(ActionEvent actionEvent) throws IOException {
        saveStep3();
        showView("reg2.fxml");
    }

    public void clickCheckAgree(ActionEvent actionEvent) {
        if (checkBoxAgree.isSelected()) {
            finishReg.setDisable(false);
        }
        else {
            finishReg.setDisable(true);
        }
    }

    public void onRegBackClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("base_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setScene(scene);
    }
}