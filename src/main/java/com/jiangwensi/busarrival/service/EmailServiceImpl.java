package com.jiangwensi.busarrival.service;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${spring.mail.username}")
    private String toEmail;

    @Override
    public boolean emailDeveloper(String userEmail, String name, String message) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(userEmail);
        msg.setTo(toEmail);
        msg.setCc(userEmail);

        msg.setSubject("Message from "+name +" - "+userEmail);
        msg.setText(message);

        try {
            javaMailSender.send(msg);
        } catch (Exception e){
            log.error("Failed to send email",e);
            return false;
        }
        return true;
    }
}
