package com.example.imkercloudserver.service.impl;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.MailService;

import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    public void SendMail(String Subject,String Content,List <User> users) {
        for(User user : users){
            SendMail(Subject, Content, user);

        }
    }
private void SendMail(String Subject,String Content, User user)
{
        // Recipient's email ID needs to be mentioned.
        String to = user.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "beehiveimkerag@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("beehiveimkerag@gmail.com", "Init123!");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            //message.setSubject("Warnung! Gewicht zu hoch!");
            message.setSubject(Subject);

            // Now set the actual message
            //message.setText("Achtung!Gewicht zu hoch!. Das Anfangsgewicht war :"+weight);
            message.setText(Content);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}