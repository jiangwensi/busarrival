package com.jiangwensi.busarrival.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/**
 * Created by Jiang Wensi on 17/7/2020
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${contact.fromEmail}")
    private String fromEmail;

    @Value("${contact.toEmail}")
    private String toEmail;

    @Override
    public boolean emailDeveloper(String userEmail, String name, String message) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("jiangwensiapp@gmail.com");
        msg.setTo("jiangwensiapp@gmail.com");
        msg.setSubject("Message from "+name +" - "+userEmail);
//        msg.setText(message);
        msg.setText("test message");
        try {
            javaMailSender.send(msg);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
