package com.example.imkercloudserver.service.impl;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.MailService;
import com.example.imkercloudserver.service.impl.types.EMailSubjectType;

import org.springframework.stereotype.Service;

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
    public void sendMailToMultipleUsers(EMailSubjectType type,Optional<Number> sufix, List <User> users) throws BusinessException {
        for(User user : users){
            sendMailToUser( type, sufix, user.getEmail());

        }
    }
    
    private void sendMailToUser(EMailSubjectType type,Optional<Number> sufix, String userEmail) throws BusinessException{
       
        // Sender's email ID needs to be mentioned
        String from = email;

        
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", emailHost);
        properties.put("mail.smtp.port", emailPort);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(email, emailPassword);

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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));

            // Set Subject: header field
            //message.setSubject("Warnung! Gewicht zu hoch!");
            message.setSubject(type.subject);
            
            // Now set the actual message
            if(sufix.isPresent()){
                message.setText(type.message+sufix.get());

            }else{
                message.setText(type.message);
            }
            
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            throw new BusinessException("Could not sent email to user"+userEmail);
        }

    }

}