package com.example.imkercloudserver.service.impl;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.MailService;
import com.example.imkercloudserver.service.impl.types.EMailSubjectType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

@Service
public class MailServiceImpl implements MailService {
    @Value("${imker.email.address}")
    private String email;
    @Value("${imker.email.password}")
    private String emailPassword;
    @Value("${imker.email.host}")
    private String emailHost;
    @Value("${imker.email.port}")
    private String emailPort;

    @Override
    public void sendMailToMultipleUsers(final EMailSubjectType type, final Optional<Number> sufix, final Set<User> users) throws BusinessException {
        for (final User user : users) {
            this.sendMailToUser(type, sufix, user.getEmail());

        }
    }

    private void sendMailToUser(final EMailSubjectType type, final Optional<Number> sufix, final String userEmail) throws BusinessException {

        // Sender's email ID needs to be mentioned
        final String from = this.email;


        // Get system properties
        final Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", this.emailHost);
        properties.put("mail.smtp.port", this.emailPort);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        final Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(MailServiceImpl.this.email, MailServiceImpl.this.emailPassword);

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            final MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));

            // Set Subject: header field
            //message.setSubject("Warnung! Gewicht zu hoch!");
            message.setSubject(type.subject);

            // Now set the actual message
            if (sufix.isPresent()) {
                message.setText(type.message + sufix.get());

            } else {
                message.setText(type.message);
            }

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (final MessagingException mex) {
            mex.printStackTrace();
            throw new BusinessException("Could not sent email to user" + userEmail);
        }

    }

}