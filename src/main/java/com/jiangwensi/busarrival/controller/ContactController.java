package com.jiangwensi.busarrival.controller;

import com.jiangwensi.busarrival.domain.dto.ContactForm;
import com.jiangwensi.busarrival.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Created by Jiang Wensi on 17/7/2020
 */
@Controller
@Slf4j
public class ContactController {

    @Autowired
    private EmailService emailService;

    @GetMapping("contact")
    public String showContactForm(Model model){
        model.addAttribute("contactForm",new ContactForm());
        return "contact";
    }

    @PostMapping("submitContact")
    public String submitContact (@Valid ContactForm contactForm, Model model, BindingResult bindingResult) {

        log.info(contactForm.getYourEmail());
        log.info(contactForm.getYourName());
        log.info(contactForm.getYourMessage());

        Boolean success = emailService.emailDeveloper(contactForm.getYourEmail(),contactForm.getYourName(),
                contactForm.getYourMessage());
        if (success) {
            model.addAttribute("contactSuccess","Your message has been sent successfully");
        } else {
            model.addAttribute("contactFail","Something went wrong, your message is not sent.");
        }

        return "contact";
    }
}
