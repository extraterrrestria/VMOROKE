package ru.hse.vmoroke;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import ru.hse.vmoroke.vk.Vk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.hse.vmoroke.App.mainStage;
import static ru.hse.vmoroke.App.vk;


/**
 * Контроллер для главного экрана приложения.
 *
 * @author user
 */

public class MainController {
    @FXML
    public Button vkAuthButton;

    @FXML
    public Button vkWallButton;

    @FXML
    private Button user_exit;
    @FXML
    private Button admin_exit;
    @FXML
    private Button analytic_exit;

    @FXML
    private CheckBox vk_comment_check;

    @FXML
    private CheckBox vk_wall_check;

    @FXML
    private ScrollPane history;

    public String comments;

    @FXML
    private FlowPane historyPane;

    @FXML
    private Tab historyTab;



    /**
     * Обработчик события нажатия кнопки "Авторизация ВКонтакте".
     *
     * @throws Exception если возникла ошибка при выполнении операции
     */

    @FXML
    void onVkButtonClick() throws Exception {
        vk = new Vk(App.hostServices, this);
        vk.authenticateStart();
    }
    /**
     * Обработчик события нажатия кнопки "Получить комментарии со стены ВКонтакте".
     *
     * @param event событие нажатия кнопки
     * @throws Exception если возникла ошибка при выполнении операции
     */

    @FXML
    void onVkDoWallClick(ActionEvent event) throws Exception {
        if (vk_wall_check.isSelected()){
            for (String i : vk.processUsersWallComments()){
                comments += i;
            }
        }
        if (vk_comment_check.isSelected()){

           VkThread vkThread = new VkThread();
           vkThread.start();

        }



    }

    private class VkThread extends Thread{
        @Override
        public void run(){
            try {
                String[] b = vk.getUsersCommentsInGroupsArray(vk.getUsersGroups());
                for (String i : b){
                    comments += i;
                }
                App.saveEmotions(comments);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
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
    void historyLoad(Event event) {
        ArrayList <String> arrayList = Emotions.getEmotionsFileData();
        for(String a : arrayList ){
            String [] b = a.split(";");
            if (b[0].equals(LoginController.login_base)){
                Label label = new Label();
                label.setText(a);
                historyPane.getChildren().add(label);
            }
        }

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


