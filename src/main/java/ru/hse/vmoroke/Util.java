package ru.hse.vmoroke;

import javafx.scene.control.Alert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Обработка исключения по вашему усмотрению
        }

        return null;
    }

    /**
     * Проверяет, соответствует ли пароль требованиям.
     * @param password Пароль для проверки.
     * @return {@code true}, если пароль соответствует требованиям, {@code false} в противном случае.
     */
    public static boolean checkPassword(String password) {
        if (password.length() < 8)
            return false;

        boolean checkLower = false;
        boolean checkUpper = false;
        boolean checkDigit = false;
        boolean checkSpec = false;
        String specChars = "#$%^&*@!?";

        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                checkUpper = true;
            }
            if (Character.isLowerCase(password.charAt(i))) {
                checkLower = true;
            }
            if (Character.isDigit(password.charAt(i))) {
                checkDigit = true;
            }
            if (specChars.contains(password.charAt(i) + "")) {
                checkSpec = true;
            }
        }

        return checkLower && checkUpper && checkDigit && checkSpec;
    }

    /**
     * Показывает диалоговое окно с сообщением.
     * @param title Заголовок диалогового окна.
     * @param content Содержимое сообщения.
     */
    public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(content);
        alert.showAndWait();
    }
}
