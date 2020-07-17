package com.jiangwensi.busarrival.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by Jiang Wensi on 17/7/2020
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean emailDeveloper(String fromAddress, String name, String message) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("jiangwensiapp@gmail.com");
        msg.setTo("jiangwensi@hotmail.com");
        msg.setSubject("Message from "+name +" - "+fromAddress);
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
