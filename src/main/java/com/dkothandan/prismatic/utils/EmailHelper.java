package com.dkothandan.prismatic.utils;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailHelper {

    @Value("${SENDGRID_API_KEY}")
    private String sendGridAPIKey;

    public void sendEmail(String emailFrom, String emailTo, String subject, String message) {
        Email from = new Email(emailFrom);
        Email to = new Email(emailTo);
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridAPIKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
