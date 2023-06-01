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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Контроллер для управления входом в систему.
 */
public class LoginController {


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
        try (BufferedReader br = new BufferedReader(new FileReader(FilesDirectory.getFileName() + "Logins and passwords.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
       /*         Pattern p = Pattern.compile("\s");
                Matcher m = p.matcher(line);
                if (m.matches()) {
                    login_base =  m.group(1);
                    h_pass_base = m.group(2);
                    System.out.println(2);
                } */
                if (line.substring(0, line.indexOf(" ")).equals(login.getText())) {
                    login_base = line.substring(0, line.indexOf(" "));
                    h_pass_base = line.substring(line.indexOf(" ")+1);
                    break;
                }
            }
            if (!(login.getText().isBlank()) && (login.getText().equals(login_base))) {
                Boolean check1 = password.getText().isBlank();
                String pass = RegistrationDataSaver.hashPassword(password.getText());
                Boolean check2 = pass.equals(h_pass_base);

                if (!check1 && check2) {
//                        setLogin(login_base);
                        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("continue.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = App.mainStage;
                        stage.setScene(scene);
                    }
                }
            }catch (IOException e) {
            e.printStackTrace();
        }
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