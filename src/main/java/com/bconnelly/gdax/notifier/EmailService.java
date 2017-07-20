package com.bconnelly.gdax.notifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * Created by Bryan on 7/19/2017.
 */

@Service
@PropertySource("classpath:application.properties")
public class EmailService {

//    @Value("${service.mail.from}")
    private static String from = "gdax-notifier@swagdaddy.com";

//    @Value("${service.mail.to}")
    private static String to = "bryan.p.connelly@gmail.com";

    /**
     * Send an email with the given subject and body to me
     * @param subject
     * @param body
     */

    public static void sendMail(String subject, String body){
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(subject);
            System.out.println("set subject");
            message.setText(body);
            System.out.println("set body");

            Transport.send(message);
            System.out.println("sent");
        } catch(AddressException e){
            System.out.println("Error parsing address: " + from);
        } catch(MessagingException e){
            System.out.println("Error in message creation/building/sending");
            e.printStackTrace();
        }
    }

}
