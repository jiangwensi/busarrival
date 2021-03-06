package com.jiangwensi.busarrival.controller;

import com.jiangwensi.busarrival.form.ContactForm;
import com.jiangwensi.busarrival.service.SESService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by Jiang Wensi on 17/7/2020
 */
@Controller
@Slf4j
public class ContactController {

    @Autowired
    private SESService emailService;


    @GetMapping("/contact")
    public String showContactForm(Model model){
        model.addAttribute("contactForm",new ContactForm());
        return "contact";
    }

    @RequestMapping(value="submitContact",method=RequestMethod.POST)
    public String submitContact (@ModelAttribute("contactForm") @Valid ContactForm contactForm,
                                 RedirectAttributes redirectAttributes, Model model,
                                 BindingResult bindingResult) {

        log.info("submitContact()");
        if (bindingResult.hasErrors()) {
            model.addAttribute("contactForm",contactForm);
            return "contact";
        }

        log.info(contactForm.getYourEmail());
        log.info(contactForm.getYourName());
        log.info(contactForm.getYourMessage());

        Boolean success = emailService.sendEmail(contactForm.getYourEmail(),contactForm.getYourName(),
                contactForm.getYourMessage());
        model.addAttribute("contactForm",new ContactForm());
        if (success) {
            redirectAttributes.addFlashAttribute("contactSuccess","Your message has been sent successfully");
        } else {
            redirectAttributes.addFlashAttribute("contactFail","Something went wrong, your message is not sent.");
        }

        return "redirect:/contact";
    }
}
