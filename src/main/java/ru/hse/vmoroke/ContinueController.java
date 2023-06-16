package ru.hse.vmoroke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ContinueController {
    @FXML
    private Label user;

    public static String role_c;
    public static String fname_c;
    public static String mname_c;
    public static String lname_c;
    public static String birth_c;
    public static String email_c;

    @FXML
    void labelText(){
        user.setText("Добрый день, " + LoginController.login_base);
    }



    @FXML
    void onContinueClick(ActionEvent event) throws IOException {
        try (BufferedReader br3 = new BufferedReader(new FileReader(FilesDirectory.getFileName() + "Login_Data.txt"))) {
            String line_datac;
            while ((line_datac = br3.readLine()) != null) {
//                            Pattern p2 = Pattern.compile(" ");
//                            Matcher m2 = p2.matcher(line_data);
                String [] splitted_line= line_datac.split(";") ;
                if (splitted_line.length == 11 && splitted_line[0].equals(LoginController.login_base)) {
                      role_c = splitted_line[2];
                    lname_c = splitted_line[3];
                    fname_c = splitted_line[4];
                    mname_c = splitted_line[5];
                    birth_c = splitted_line[6];
                    email_c = splitted_line[7];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (role_c) {
            case ("Пользователь"):
                FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("user_profile.fxml"));
                Scene scene1 = new Scene(fxmlLoader1.load());
                Stage stage1 = App.mainStage;
                stage1.setTitle(LoginController.login_base);
                stage1.setScene(scene1);
                break;

            case ("Аналитик"):
                FXMLLoader fxmlLoader2 = new FXMLLoader(App.class.getResource("analytic_profile.fxml"));
                Scene scene2 = new Scene(fxmlLoader2.load());
                Stage stage2 = App.mainStage;
                stage2.setTitle(LoginController.login_base);
                stage2.setScene(scene2);
                break;

            case ("Администратор"):
                FXMLLoader fxmlLoader3 = new FXMLLoader(App.class.getResource("admin_profile.fxml"));
                Scene scene3 = new Scene(fxmlLoader3.load());
                Stage stage3 = App.mainStage;
                stage3.setTitle(LoginController.login_base);
                stage3.setScene(scene3);
                break;
        }

    }

}

