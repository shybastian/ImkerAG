package com.example.imkercloudserver.service.impl;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.MailService;
import com.example.imkercloudserver.service.impl.model.Mail;
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
            final Mail mail = new Mail(type.subject, type.message);
            if (sufix.isPresent()) {
                this.sendMailToUser(mail, sufix.get(), user.getEmail());
            } else {
                this.sendMailToUser(mail, null, user.getEmail());
            }
        }
    }

    @Override
    public void sendMailToUser(final String userEmail, final Long beehiveId, final EMailSubjectType type) throws BusinessException {
        final Mail mail = new Mail(type.subject, type.message);
        this.sendMailToUser(mail, beehiveId, userEmail);
    }

    //@Override
    public void sendMailToUser(final Mail mail, final Number sufix, final String email) throws BusinessException {
        if (mail == null) return;

        final Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.host", this.emailHost);
        properties.put("mail.smtp.port", this.emailPort);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        final Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailServiceImpl.this.email, MailServiceImpl.this.emailPassword);
            }

        });
        try {
            // Create a default MimeMessage object.
            final MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(this.email));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(mail.getSubject());
            // Now set the actual message
            message.setText(mail.getMessage() + sufix);
            // Send message
            Transport.send(message);
        } catch (final MessagingException mex) {
            mex.printStackTrace();
            throw new BusinessException("Could not sent email to user" + email);
        }
    }
}