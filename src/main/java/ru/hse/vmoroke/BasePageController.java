package ru.hse.vmoroke;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class BasePageController {

    @FXML
    private Button basereg;
    @FXML
    private Button baselog;
   // @FXML
 //   private Hyperlink instruction;

    @FXML
    private Button angry;
    @FXML
    private Button cool;
    @FXML
    private Button crying;
    @FXML
    private Button happy;
    @FXML
    private Button love;
    @FXML
    private Button money;
    @FXML
    private Button shocked;
    @FXML
    private Button thoughtful;
    @FXML
    private Button weird;
    @FXML
    private Label emojiresponse;
    @FXML
    private Label emojiresponse2;

    /**
     * Обработчик нажатия кнопки "Злой".
     */
    @FXML
    void AngryClick(){
        emojiresponse.setText("Не держи в себе! Кричи, бей подушку!");
        emojiresponse2.setText("");
    }

    /**
     * Обработчик нажатия кнопки "Круто".
     */
    @FXML
    void CoolClick(){
        emojiresponse.setText("Еее! Так держать!");
        emojiresponse2.setText("");
    }

    /**
     * Обработчик нажатия кнопки "Плачущий".
     */
    @FXML
    void CryingClick(){
        emojiresponse.setText("Смотри! Это знак, что все скоро наладится ");
        emojiresponse2.setText("");
    }

    /**
     * Обработчик нажатия кнопки "Счастливый".
     */
    @FXML
    void HappyClick(){
        emojiresponse.setText("Отлично! Успех - это хорошее настроение!");
        emojiresponse2.setText("");
    }

    /**
     * Обработчик нажатия кнопки "Любовь".
     */
    @FXML
    void LoveClick(){
        emojiresponse.setText("А как глаза блестят!");
        emojiresponse2.setText("");
    }

    /**
     * Обработчик нажатия кнопки "Деньги".
     */
    @FXML
    void MoneyClick(){
        emojiresponse.setText("Важнее всего иметь хорошее настроение, ");
        emojiresponse2.setText("остальное - вопрос денег!");
    }

    /**
     * Обработчик нажатия кнопки "Шокированный".
     */
    @FXML
    void ShockedClick(){
        emojiresponse.setText("В шоке? Получается перед экраном звезда!");
        emojiresponse2.setText("");
    }

    /**
     * Обработчик нажатия кнопки "Задумчивый".
     */
    @FXML
    void ThoughtfulClick(){
        emojiresponse.setText("Кто спит в 3 часа ночи? Самое время подумать!");
        emojiresponse2.setText("");
    }

    /**
     * Обработчик нажатия кнопки "Странный".
     */
    @FXML
    void WeirdClick(){
        emojiresponse.setText("Жизнь слишком важна, ");
        emojiresponse2.setText("чтобы рассуждать о ней серьезно!");
    }

    /**
     * Обработчик нажатия кнопки "Зарегистрироваться".
     *
     * @throws IOException если возникают проблемы с загрузкой FXML-файла
     */

    @FXML
    protected void OnBRegButtonClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        //   stage.setTitle(password.getText());
        stage.setScene(scene);
    }

    /**
     * Обработчик нажатия кнопки "Войти".
     *
     * @throws IOException если возникают проблемы с загрузкой FXML-файла
     */
    @FXML
    protected void onLogButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        //      stage.setTitle(password.getText());
        stage.setScene(scene);
    }


}
