package ru.hse.vmoroke;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.nio.file.Files;


public class App extends Application {
    public static Stage mainStage;
    public static HostServices hostServices;


    @Override
    public void start(Stage stage) throws Exception {
        FilesDirectory.filesDirectoryName();
        System.out.println(FilesDirectory.getFileName());
        FilesDirectory.ExistenceOfFiles(FilesDirectory.getFileName());
        hostServices = getHostServices();
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("base_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Vmoroke");
        stage.setScene(scene);
        stage.getIcons().add(new Image("vmlogo.png"));
        stage.show();
        getHostServices();
    }

    public static void main(String[] args) {
        launch();
    }


    //private static void handleRequest(HttpExchange exchange) throws IOException {
    //}
}
