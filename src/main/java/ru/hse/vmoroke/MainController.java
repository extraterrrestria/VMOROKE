package ru.hse.vmoroke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    private Vk vk;

    /**
     * Обработчик события нажатия кнопки "Авторизация ВКонтакте".
     *
     * @param event событие нажатия кнопки
     * @throws Exception если возникла ошибка при выполнении операции
     */

    @FXML
    void onVkButtonClick(ActionEvent event) throws Exception {
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

}

