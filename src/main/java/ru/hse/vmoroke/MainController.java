package ru.hse.vmoroke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Контроллер для главного экрана приложения.
 *
 * @author user
 */

public class MainController {
    @FXML
    Button vkAuthButton;

    @FXML
    Button vkWallButton;

    @FXML
    private Button user_exit;
    @FXML
    private Button admin_exit;
    @FXML
    private Button analytic_exit;

    private Vk vk;

    /**
     * Обработчик события нажатия кнопки "Авторизация ВКонтакте".
     *
     * @throws Exception если возникла ошибка при выполнении операции
     */

    @FXML
    void onVkButtonClick() throws Exception {
        System.out.println("1");
        vk = new Vk(App.hostServices, this);
        vk.authenticateStart();
        System.out.println("2");
    }
    /**
     * Обработчик события нажатия кнопки "Получить комментарии со стены ВКонтакте".
     *
     * @param event событие нажатия кнопки
     * @throws Exception если возникла ошибка при выполнении операции
     */

    @FXML
    void onVkDoWallClick(ActionEvent event) throws Exception {
        vk.processWallComments();
    }

    /**
     * Обработчик события нажатия кнопки "Выход из аккаунта".
     *
     * @throws IOException если возникла ошибка ввода-вывода при загрузке ресурсов
     */

    @FXML
    void onUExitClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("base_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setScene(scene);
    }


    @FXML
    private Label username_u;
    @FXML
    private Label f_name_u;
    @FXML
    private Label l_name_u;
    @FXML
    private Label m_name_u;
    @FXML
    private Label birth_u;
    @FXML
    private Label email_u;
    @FXML
    private Label username_an;
    @FXML
    private Label f_name_an;
    @FXML
    private Label l_name_an;
    @FXML
    private Label m_name_an;
    @FXML
    private Label birth_an;
    @FXML
    private Label email_an;
    @FXML
    private Label username_ad;
    @FXML
    private Label f_name_ad;
    @FXML
    private Label l_name_ad;
    @FXML
    private Label m_name_ad;
    @FXML
    private Label birth_ad;
    @FXML
    private Label email_ad;

    public void DataU (){
        username_u.setText(LoginController.login_base);
        f_name_u.setText(ContinueController.fname_c);
        l_name_u.setText(ContinueController.lname_c);
        m_name_u.setText(ContinueController.mname_c);
        birth_u.setText(ContinueController.birth_c);
        email_u.setText(ContinueController.email_c);
    }
    public void DataAn (){
        username_an.setText(LoginController.login_base);
        f_name_an.setText(ContinueController.fname_c);
        l_name_an.setText(ContinueController.lname_c);
        m_name_an.setText(ContinueController.mname_c);
        birth_an.setText(ContinueController.birth_c);
        email_an.setText(ContinueController.email_c);
    }
    public void DataAd (){
        username_ad.setText(LoginController.login_base);
        f_name_ad.setText(ContinueController.fname_c);
        l_name_ad.setText(ContinueController.lname_c);
        m_name_ad.setText(ContinueController.mname_c);
        birth_ad.setText(ContinueController.birth_c);
        email_ad.setText(ContinueController.email_c);
    }

}


