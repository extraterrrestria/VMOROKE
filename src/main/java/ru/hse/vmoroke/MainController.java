
package ru.hse.vmoroke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    Button vkAuthButton;

    @FXML
    Button vkWallButton;

    @FXML
    private Button user_exit;

    private Vk vk;

    @FXML
    void onVkButtonClick(ActionEvent event) throws Exception {
        vk = new Vk(App.hostServices, this);
        vk.authenticateStart();
    }

    @FXML
    void onVkDoWallClick(ActionEvent event) throws Exception {
        vk.processWallComments();
    }

    @FXML
    void onUExitClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("base_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.mainStage;
        stage.setScene(scene);
    }

}

