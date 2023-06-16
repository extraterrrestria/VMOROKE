package ru.hse.vmoroke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Контроллер для управления входом в систему.
 */
public class LoginController {
    final static int MAX_COUNT_ATTEMPT = 3;
    private int currentAttempt = 0;

    @FXML
    private PasswordField password;
    @FXML
    private TextField login;
    @FXML
    private Button logback;

    /**
     * Обработчик события нажатия кнопки "Test".
     *
     * @param event событие нажатия кнопки
     */
    @FXML
    void test(ActionEvent event) {
        Stage stage = App.mainStage;
        stage.setTitle(login.getText());

    }

    public void setLogin(String p_login){
        this.p_login = p_login;
    }
    public String getP_login(){
        return this.p_login;
    }

    public static String login_base = "";

    public String h_pass_base = "";
    public String p_login = "";

    /**
     * Обработчик события нажатия кнопки "Register".
     *
     * @param event событие нажатия кнопки
     * @throws IOException если возникла ошибка ввода-вывода при загрузке ресурсов
     */
    @FXML
    void onRegisterClick(ActionEvent event) throws IOException  {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setScene(scene);
    }
    /**
     * Обработчик события нажатия кнопки "Login".
     *
     * @throws IOException если возникла ошибка ввода-вывода при загрузке ресурсов
     */
    @FXML
    public void onLoginClick() throws IOException {
        String loginText = login.getText();
        String pass = Util.hashPassword(password.getText());
        List<Person> personList = PersonRepository.getInstance().getPersons();
        Optional<Person> optionalPerson = personList.stream().
                filter(t->t.getLogin().equals(loginText) && t.getPassword().equals(pass)).
                findFirst();
        login_base = loginText;
        if (optionalPerson.isPresent()) {
            if (optionalPerson.get().isBlocked()) {
                Util.showAlert("Ошибка входа", "Вы заблокированы");
            }
            else {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("continue.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = App.mainStage;
                stage.setScene(scene);
            }
        }
        else {
            if (personList.stream().anyMatch(t->t.getLogin().equals(loginText))) {
                if (personList.stream().
                        filter(t->t.getLogin().equals(loginText)).
                        findFirst().get().isBlocked()) {
                    Util.showAlert("Ошибка входа", "Вы заблокированы");
                }
                else {
                    showAlert("Ошибка", "Неверный логин или пароль");
                    currentAttempt++;

                    if (currentAttempt == MAX_COUNT_ATTEMPT) {
                        showAlert("Ошибка", "Количество попыток для входа достигло предела. Вы заблокированы)");
                        Person person = personList.stream().filter(t -> t.getLogin().equals(loginText)).findFirst().get();

                        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("secret_answer_view.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        SecretAnswerController controller = fxmlLoader.getController();
                        controller.setCurrentUser(person);
                        Stage stage = App.mainStage;
                        stage.setScene(scene);
                    }
                }
            }
            else {
                showAlert("Ошибка", "Пользователь не найден");
            }
        }
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(content);
        alert.showAndWait();
    }

    /**
     * Обработчик события нажатия кнопки "Log Back".
     *
     * @throws IOException если возникла ошибка ввода-вывода при загрузке ресурсов
     */
    @FXML
    void onLogBackClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("base_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setScene(scene);
    }

    public void showBug(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(content);
        alert.showAndWait();
    }

}