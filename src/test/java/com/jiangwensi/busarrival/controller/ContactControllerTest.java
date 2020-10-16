package com.jiangwensi.busarrival.controller;

import com.jiangwensi.busarrival.form.ContactForm;
import com.jiangwensi.busarrival.service.SESService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @MockBean
    private SESService sesService;

    @Autowired
    MockMvc mockMvc;

    @Captor
    ArgumentCaptor<String> captor;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void showContactForm() throws Exception {
        mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"))
                .andExpect(model().attributeExists("contactForm"))
                .andExpect(model().attribute("contactForm", new ContactForm()));

    }

    @Test
    void submitContactSuccess() throws Exception {
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> yourMessageCaptor = ArgumentCaptor.forClass(String.class);
        String yourEmail = "test@email.com";
        String yourName = "test name";
        String yourMessage = "test message";

        given(sesService.sendEmail(emailCaptor.capture(),nameCaptor.capture(),yourMessageCaptor.capture())).willReturn(true);

        RequestBuilder request = post("/submitContact")
                .param("yourEmail", yourEmail)
                .param("yourName", yourName)
                .param("yourMessage", yourMessage);

        mockMvc.perform(request)
                .andExpect(redirectedUrl("/contact"))
                .andExpect(flash().attributeExists("contactSuccess"))
                .andExpect(status().is3xxRedirection());

        assertEquals(yourEmail,emailCaptor.getValue());
        assertEquals(yourName,nameCaptor.getValue());
        assertEquals(yourMessage,yourMessageCaptor.getValue());
    }

    @Test
    void submitContactFailed() throws Exception {
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> yourMessageCaptor = ArgumentCaptor.forClass(String.class);
        String yourEmail = "test@email.com";
        String yourName = "test name";
        String yourMessage = "test message";

        given(sesService.sendEmail(emailCaptor.capture(),nameCaptor.capture(),yourMessageCaptor.capture())).willReturn(false);

        RequestBuilder request = post("/submitContact")
                .param("yourEmail", yourEmail)
                .param("yourName", yourName)
                .param("yourMessage", yourMessage);

        mockMvc.perform(request)
                .andExpect(redirectedUrl("/contact"))
                .andExpect(flash().attributeExists("contactFail"))
                .andExpect(status().is3xxRedirection());

        assertEquals(yourEmail,emailCaptor.getValue());
        assertEquals(yourName,nameCaptor.getValue());
        assertEquals(yourMessage,yourMessageCaptor.getValue());
    }

    @Test
    void submitContactValidationError() throws Exception {
        String yourEmail = "invalidEmail";
        String yourName = "test name";
        String yourMessage = "test message";

        RequestBuilder request = post("/submitContact")
                .param("yourEmail", yourEmail)
                .param("yourName", yourName)
                .param("yourMessage", yourMessage)
                .requestAttr(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE,new FlashMap());
        //TODO to verify that yourEmail has validation error
        //.andExpect(model().attributeHasFieldErrors("yourEmail")) has error "modelandview not found"

        mockMvc.perform(request)
//                .andExpect(model().attributeHasFieldErrors("yourEmail"))
                .andExpect(status().is4xxClientError());
    }
}