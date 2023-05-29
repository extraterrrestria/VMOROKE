package ru.hse.vmoroke;

import java.io.File;
import java.net.URISyntaxException;

public class FilesDirectory {
    private static String  fileVar;
    public static String getFileName(){
        return fileVar;
    }
    public static void CheckExistenceOfFiles(String path){
        File file = new File(path, "Logins and passwords");

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
