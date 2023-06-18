package ru.hse.vmoroke;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class FilesDirectory {
    private static String  fileVar;
    public static String getFileName(){
        return fileVar;
    }
    public static void ExistenceOfFiles(String path){

        try {
            new File(path+"\\\\Logins and passwords.txt").createNewFile();
            new File(path+"\\\\Login_Data.txt").createNewFile();
            new File(path+"\\\\UsersEmotionsData.txt").createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void filesDirectoryName (){
        String pathToJar = null;
        try {
            pathToJar = new File(App.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        fileVar = pathToJar.replaceAll("\\\\[^\\\\]+$","\\\\");
    }
}
