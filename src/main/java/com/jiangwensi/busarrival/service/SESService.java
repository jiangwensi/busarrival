package com.jiangwensi.busarrival.service;

/**
 * Created by Jiang Wensi on 16/8/2020
 */
public interface SESService {

    boolean sendEmail(String userEmail, String userName, String message);

//    void sendEmailVerification(UserDto userDto);
//    void sendResetForgottenPasswordTokenEmail(UserDto userDto, TokenDto tokenDto);
}
