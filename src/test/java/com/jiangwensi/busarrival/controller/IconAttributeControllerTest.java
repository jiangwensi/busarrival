package com.jiangwensi.busarrival.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IconAttributeControllerTest {

    @InjectMocks
    IconAttributeController iconAttributeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(iconAttributeController).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void showIconAttributes() throws Exception {
        mockMvc.perform(get("/icons"))
                .andExpect(view().name("iconAttribute"))
                .andExpect(status().isOk()).andExpect(model().attributeExists("icons"));
    }
}