package ru.hse.vmoroke;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrationDataSaver {
    public static void saveRegistrationData(String login, String password) {
        String hashedPassword = hashPassword(password);

        try (PrintWriter writer = new PrintWriter(new FileWriter("Logins and passwords.txt", true))) {
            writer.println("Login: " + login);
            writer.println("Hashed Password: " + hashedPassword);
            writer.println();
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка исключения по вашему усмотрению
        }
    }

    private static String hashPassword(String password) {
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