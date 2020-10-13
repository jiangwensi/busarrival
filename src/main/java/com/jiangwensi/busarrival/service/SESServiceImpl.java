package com.jiangwensi.busarrival.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Jiang Wensi on 13/10/2020
 */
@Service
public class SESServiceImpl implements SESService {

    @Value("${aws.accessKeyId}")
    private String awsAccessKey;
    @Value("${aws.secretKey}")
    private String awsSecretKey;
    @Value("${aws.email.from}")
    private String awsEmail;


    @Override
    public boolean sendEmail(String userEmail, String userName, String message) {
        System.setProperty("aws.accessKeyId",awsAccessKey);
        System.setProperty("aws.secretKey",awsSecretKey);

        String subject = "[SG Bus App]:Email from "+userName+" ("+userEmail+")";
        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            // Replace US_WEST_2 with the AWS Region you're using for
                            // Amazon SES.
                            .withRegion(Regions.AP_SOUTHEAST_1).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(awsEmail).withCcAddresses(userEmail))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(message)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(subject)))
                    .withSource(awsEmail);

            client.sendEmail(request);
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
            return false;
        }
        return true;
    }
}
