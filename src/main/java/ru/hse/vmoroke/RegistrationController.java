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
import java.util.List;
import java.util.ResourceBundle;

/**
 * Класс отвечает за обработку регистрации пользователя.
 */

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

    /**
     * Инициализирует контроллер.
     * @param url Местоположение для разрешения относительных путей для корневого объекта.
     * @param resourceBundle Ресурсы, используемые для локализации корневого объекта.
     */
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

    /**
     * Отображает информацию о пользователе.
     */
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
    /**
     * Инициализирует контроллер объектом Person.
     * @param p Объект Person для инициализации.
     */
    public void init(Person p) {
        this.p = p;

        showPerson();
    }
    /**
     * Проверяет, является ли логин уникальным.
     * @param login Логин для проверки.
     * @return {@code true}, если логин уникален, {@code false} в противном случае.
     */
    private boolean checkUnique(String login) {
        return PersonRepository.getInstance().getPersons().stream().noneMatch(t -> t.getLogin().equals(login));
    }


    /**
     * Обработчик события регистрации.
     *
     * Выполняет регистрацию пользователя на основе введенных данных.
     *
     * @param event Событие нажатия кнопки регистрации.
     * @throws IOException Исключение, возникающее при ошибке ввода-вывода.
     */
    @FXML
    void onRegister(ActionEvent event) throws IOException {
        String login = username.getText();
        String passwordValue = password.getText();
        String repasswordValue = repassword.getText();
        String role = userRole.getValue();

        if (!checkUnique(login)) {
            Util.showAlert("", "Такой логин уже занят");
            return;
        }

        if (!Util.checkPassword(passwordValue)) {
            Util.showAlert("Проверка пароля", "Пароль не соответсвует требованиям");
            return;
        }

        if (!passwordValue.equals(repasswordValue)) {
            Util.showAlert("Проверка пароля", "Пароли не совпадают");
            return;
        }

        if (checkEmpty(login)) {
            Util.showAlert("Проверка логина", "Введите логин");
            return;
        }

        if (userRole.getSelectionModel().getSelectedIndex() == -1) {
            Util.showAlert("Проверка роли", "Выберите роль");
            return;
        }

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

    /**
     * Отображает представление для шага 2 регистрации.
     *
     * Переходит к шагу 2 регистрации и сохраняет введенные данные.
     *
     * @param event Событие нажатия кнопки.
     * @throws IOException Исключение, возникающее при ошибке ввода-вывода.
     */
    @FXML
    void onRegisterStep2(ActionEvent event) throws IOException {
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

        saveStep2();

        showView("reg3.fxml");
    }
    /**
     * Выполняет переход к представлению для шага 3 регистрации.
     *
     * Переходит к шагу 3 регистрации и сохраняет введенные данные.
     *
     * @param actionEvent Событие нажатия кнопки.
     * @throws IOException Исключение, возникающее при ошибке ввода-вывода.
     */
    public void onRegisterStep3(ActionEvent actionEvent) throws IOException {
        if (secretQuestion.getSelectionModel().getSelectedIndex() == -1) {
            Util.showAlert("Проверка выбора вопроса", "Выберите вопрос");
            return;
        }

        if (checkEmpty(answer.getText())) {
            Util.showAlert("Проверка ответа", "Введите ответ");
            return;
        }

        saveStep3();
        // Сохранение логина и пароля
        PersonRepository.getInstance().add(p);
        PersonRepository.getInstance().save();

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