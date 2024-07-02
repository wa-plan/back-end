package com.example.waplan.mail.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleEmail(String mailTitle, String recipientm, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mailTitle);
        message.setTo(recipientm);
        message.setText(content);

        javaMailSender.send(message);
    }
}
