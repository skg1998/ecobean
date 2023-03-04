package com.infy.ekart.notification.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailNotificationConfig {
    public static final String HOST = "smtp.gmail.com";
    public static final Integer PORT = 587;
    public static final String USER_NAME = "your_username";
    public static final String PASSWORD = "your_password";
    public static final Boolean DEBUG = true;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(HOST);
        javaMailSender.setPort(PORT);

        javaMailSender.setUsername(USER_NAME);
        javaMailSender.setPassword(PASSWORD);

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", DEBUG.toString());

        return javaMailSender;
    }
}
