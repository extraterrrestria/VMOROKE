package ru.hse.vmoroke;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    public static Stage mainStage;
    public static HostServices hostServices;


    @Override
    public void start(Stage stage) throws Exception {
        hostServices = getHostServices();
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        getHostServices();
    }

    public static void main(String[] args) {
        launch();
    }


    //private static void handleRequest(HttpExchange exchange) throws IOException {
    //}
}
