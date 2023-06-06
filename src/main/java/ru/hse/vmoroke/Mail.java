package ru.hse.vmoroke;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Mail {

    public static void sendMail(String mailTo, String text, String subject) {
        try (BufferedReader br = new BufferedReader(new FileReader(FilesDirectory.getFileName() + "Credentials.sys"))) {
            String[] LoginPassword = br.readLine().split("\\s+");
        final String username = LoginPassword[0];
        final String password = LoginPassword[1];
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Vmorokeapp"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailTo)
            );
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Ошибка отправки сообщения");
        } catch (IOException e) {
            System.out.println("Нет файла или испорченные данные");
        e.printStackTrace();
    }
    }

}