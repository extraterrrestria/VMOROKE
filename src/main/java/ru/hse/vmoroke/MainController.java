
package ru.hse.vmoroke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML
    Button vkAuthButton;

    @FXML
    Button vkWallButton;

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
}

