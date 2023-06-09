package ru.hse.vmoroke;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.hse.vmoroke.vk.Vk;

/**
 * Основной класс приложения Vmoroke.
 */
public class App extends Application {
    public static Stage mainStage;
    public static HostServices hostServices;
    public static Vk vk;

    /**
     * Расчитывает частное двух чисел.
     *
     * @param divisible делимое
     * @param divisor   делитель
     * @return частное от деления
     */

    public static double quotient(int divisible, int divisor){
        return (divisible*100)/divisor * 0.01;
    }

    /**
     * Находит максимальное значение среди нескольких чисел.
     *
     * @param d числа для сравнения
     * @return максимальное значение
     */
    public static int max(int ... d){
        int res = 0;
        for(int i = 0; i < d.length; i ++){
            if(d[i] > res){res = d[i];}
        }
        return res;
    }

    /**
     * Определяет эмоциональный вердикт на основе предоставленного текста.
     *
     * @param text входной текст
     * @return эмоциональный вердикт
     */

    public static String verdict(String text){
        Emotions result = new Emotions(); //создаю экземпляр класса эмоций
        result.setAll(text); // эта функция из текста делает те самые коэф радости и тд. для экземпляра result

        if(quotient(result.emotionalLevel, result.apathy + result.emotionalLevel) < 0.06){
            return "Этот пользовательь крайне безэмоционален";
        }
        if(result.happy == result.sad
                && result.sad == result.surprise
                && result.surprise == result.angry
                && result.angry == result.disgust
                && result.disgust == result.fear){
            return "Этот пользователь в эмоциональном балансе";
        }
        int highest = max(result.happy, result.sad, result.surprise, result.angry, result.disgust, result.fear);
        if(result.happy == highest){return "Этот пользователь счастлив";}
        if(result.sad == highest){return "Этот пользователь грустный";}
        if(result.surprise == highest){return "Этот пользователь удивлён";}
        if(result.angry == highest){return "Этот пользователь раздражён";}
        if(result.disgust == highest){return "Этот пользователь испытывает отвращение";}
        if(result.fear == highest){return "Этот пользователь напуган";}

        return "Этот пользователь в эмоциональном раздрае";
        // ветвить можно очень много под разные вариации, главное - показать, что какой-то вердикт мы можем вынести
        // доработать надо, конечно
    }



    @Override
    public void start(Stage stage) throws Exception {
        FilesDirectory.filesDirectoryName();
        FilesDirectory.ExistenceOfFiles(FilesDirectory.getFileName());
        hostServices = getHostServices();
        mainStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("base_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Vmoroke");
        stage.setScene(scene);
        stage.getIcons().add(new Image("vmlogo.png"));
        stage.show();
    }

    /**
     * вход в приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
    System.out.println(verdict("я молодец, что сделал это задание"));
    launch();
    }
    @Override
    public void stop(){
        if (vk!=null) {
            vk.stopServer();
        }
        }

}
