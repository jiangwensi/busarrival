package com.jiangwensi.busarrival.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by Jiang Wensi on 17/7/2020
 */
@Data
public class ContactForm {

    @Email
    @NotBlank
    private String yourEmail;

    private String yourName;

    @NotBlank
    private String yourMessage;
}
