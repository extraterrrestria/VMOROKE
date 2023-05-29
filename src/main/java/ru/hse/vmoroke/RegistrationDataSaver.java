package ru.hse.vmoroke;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrationDataSaver {
    public static void saveRegistrationData(String login, String password) {
        String hashedPassword = hashPassword(password);

        try (PrintWriter writer = new PrintWriter(new FileWriter(FilesDirectory.getFileName() + "Logins and passwords", true))) {
            writer.println(login + " " + hashedPassword);
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка исключения по вашему усмотрению
        }

    }


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
}