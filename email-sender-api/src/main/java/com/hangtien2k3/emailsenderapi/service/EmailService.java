package com.hangtien2k3.emailsenderapi.service;

import org.springframework.stereotype.Service;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private static final String FROM_EMAIL = "taocaygithub@gmail.com";
    private static final String HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 465;

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", String.valueOf(SMTP_PORT));
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return properties;
    }

    private Session getSession() {
        return Session.getInstance(getMailProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, "*****"); // Replace with your password
            }
        });
    }

    private MimeMessage createMimeMessage(Session session, String to, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(FROM_EMAIL));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(message);
        return mimeMessage;
    }

    public boolean sendEmail(String message, String subject, String to) {
        boolean isSent = false;
        Session session = getSession();

        try {
            MimeMessage mimeMessage = createMimeMessage(session, to, subject, message);
            Transport.send(mimeMessage);
            System.out.println("Email sent successfully...");
            isSent = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return isSent;
    }
}

