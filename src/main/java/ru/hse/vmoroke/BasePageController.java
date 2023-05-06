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


    @FXML
    void AngryClick(){
        emojiresponse.setText("Не держи в себе! Кричи, бей подушку!");
        emojiresponse2.setText("");
    }
    @FXML
    void CoolClick(){
        emojiresponse.setText("Еее! Так держать!");
        emojiresponse2.setText("");
    }
    @FXML
    void CryingClick(){
        emojiresponse.setText("Смотри! Это знак, что все скоро наладится ");
        emojiresponse2.setText("");
    }
    @FXML
    void HappyClick(){
        emojiresponse.setText("Отлично! Успех - это хорошее настроение!");
        emojiresponse2.setText("");
    }
    @FXML
    void LoveClick(){
        emojiresponse.setText("А как глаза блестят!");
        emojiresponse2.setText("");
    }
    @FXML
    void MoneyClick(){
        emojiresponse.setText("Важнее всего иметь хорошее настроение, ");
        emojiresponse2.setText("остальное - вопрос денег!");
    }
    @FXML
    void ShockedClick(){
        emojiresponse.setText("В шоке? Получается перед экраном звезда!");
        emojiresponse2.setText("");
    }
    @FXML
    void ThoughtfulClick(){
        emojiresponse.setText("Кто спит в 3 часа ночи? Самое время подумать!");
        emojiresponse2.setText("");
    }
    @FXML
    void WeirdClick(){
        emojiresponse.setText("Жизнь слишком важна, ");
        emojiresponse2.setText("чтобы рассуждать о ней серьезно!");
    }

    @FXML
    protected void OnBRegButtonClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        //   stage.setTitle(password.getText());
        stage.setScene(scene);
    }
    @FXML
    protected void onLogButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        //      stage.setTitle(password.getText());
        stage.setScene(scene);
    }


}
