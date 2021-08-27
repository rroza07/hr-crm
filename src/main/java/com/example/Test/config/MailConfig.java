package com.example.Test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.username}")
    private String senderEmailID;
    @Value("${spring.mail.password}")
    private String senderPassword;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.properties.auth}")
    private String auth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String enable;
    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private String required;
    @Value("${spring.mail.properties.smtp.ssl.protocols}")
    private String protocols;
    @Value("${spring.mail.properties.mail.smtp.socketFactory.class}")
    private String socketFactory;

    public Session getSession() {

        Properties properties = new Properties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.starttls.enable", enable);
        properties.put("mail.smtp.starttls.required", required);
        properties.put("mail.smtp.ssl.protocols", protocols);
        properties.put("mail.smtp.socketFactory.class", socketFactory);

        Authenticator auth = new SMTPAuthenticator();
        return Session.getInstance(properties, auth);
    }

    public class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmailID, senderPassword);
        }
    }

    public String getSenderEmailID(){
        return this.senderEmailID;
    }
}