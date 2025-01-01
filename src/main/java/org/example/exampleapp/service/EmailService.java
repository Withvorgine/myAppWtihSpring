package org.example.exampleapp.service;

import org.example.exampleapp.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.ses.model.*;

@Component
public class EmailService {

    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretKey;

    public SendEmailRequest getSendEmailRequest(User user) {
        System.setProperty("aws.accessKeyId", accessKeyId);
        System.setProperty("aws.secretKey", secretKey);

        Message message = Message.builder()
                .subject(Content.builder()
                        .data("Customer created")
                        .charset("UTF-8")
                        .build())
                .body(Body.builder()
                        .html(Content.builder()
                                .data("Welcome to Denizer Bank. Your customer id is: " + user.getId())
                                .build())
                        .text(Content.builder()
                                .data("Customer created successfully testo")
                                .charset("UTF-8")
                                .build())
                        .build())
                .build();


        Destination destination = Destination.builder()
                .toAddresses(user.getEmail())
                .build();

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .source("denfaruk_119@hotmail.com")
                .message(message)
                .destination(destination)
                .build();
        return sendEmailRequest;
    }
}
