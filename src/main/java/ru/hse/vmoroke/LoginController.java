package ru.hse.vmoroke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginController {


    @FXML
    private PasswordField password;
    @FXML
    private TextField login;
    @FXML
    private Button logback;
    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField middleName;
    @FXML
    private TextField birthday;
    @FXML
    private TextField email;



    @FXML
    void test(ActionEvent event) {
        Stage stage = App.mainStage;
        stage.setTitle(login.getText());

    }
    @FXML
    void onRegisterClick(ActionEvent event) throws IOException  {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setScene(scene);
    }
    @FXML
    protected void onLoginClick() throws IOException {
        int number = 0;
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("Logins and passwords.txt"))) {
            String line;

            String login_base = "";
            String h_pass_base = "";
            String p_role = "";
            String p_f_n = "";
            String p_m_n = "";
            String p_l_n = "";
            String p_b_d = "";
            String p_em = "";
            String p_login = "";

            System.out.println(1);

            while ((line = br.readLine()) != null) {
                System.out.println(1.5);
       /*         Pattern p = Pattern.compile("\s");
                Matcher m = p.matcher(line);
                if (m.matches()) {
                    login_base =  m.group(1);
                    h_pass_base = m.group(2);
                    System.out.println(2);
                } */
                if (line.substring(0, line.indexOf(" ")).equals(login.getText())) {
                    login_base = login.getText();
                    h_pass_base = line.substring(line.indexOf(" "));
                    System.out.println(2);
                    break;
                }
            }
            if (!(login.getText().isBlank()) && (login.getText().equals(login_base))) {
                if (!(password.getText().isBlank()) && (RegistrationDataSaver.hashPassword(password.getText()).equals(h_pass_base))) {
                    try (BufferedReader br2 = new BufferedReader(new FileReader("Login_Data.txt"))) {
                        System.out.println(3);
                        String line_data;
                        while ((line_data = br2.readLine()) != null) {
                            Pattern p2 = Pattern.compile(" ");
                            Matcher m2 = p2.matcher(line_data);
                            if (m2.find()) {
                                p_login = m2.group(1);
                                p_role = m2.group(2);
                                p_l_n = m2.group(3);
                                p_f_n = m2.group(4);
                                p_m_n = m2.group(5);
                                p_b_d = m2.group(6);
                                p_em = m2.group(7);
                                System.out.println(4);
                            }
                        }
                        if (p_login.equals(login_base)) {
                            switch (p_role) {
                                case ("Пользователь"):
                                    FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("user_profile.fxml"));
                                    Scene scene1 = new Scene(fxmlLoader1.load());
                                    Stage stage1 = App.mainStage;
                                    stage1.setTitle(login.getText());
                                    stage1.setScene(scene1);
                                    username.setText(p_login);
                                    firstName.setText(p_f_n);
                                    lastName.setText(p_l_n);
                                    middleName.setText(p_m_n);
                                    birthday.setText(p_b_d);
                                    email.setText(p_em);
                                    System.out.println(5);
                                case ("Аналитик"):
                                    FXMLLoader fxmlLoader2 = new FXMLLoader(App.class.getResource("user_profile.fxml"));
                                    Scene scene2 = new Scene(fxmlLoader2.load());
                                    Stage stage2 = App.mainStage;
                                    stage2.setTitle(login.getText());
                                    stage2.setScene(scene2);
                                case ("Администратор"):
                                    FXMLLoader fxmlLoader3 = new FXMLLoader(App.class.getResource("user_profile.fxml"));
                                    Scene scene3 = new Scene(fxmlLoader3.load());
                                    Stage stage3 = App.mainStage;
                                    stage3.setTitle(login.getText());
                                    stage3.setScene(scene3);
                            }
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



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