package com.message.service.impl;

import com.google.gson.Gson;
import com.message.model.Message;
import com.message.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Override
    @Async
    public void sendMessageAsync(String message) throws MessagingException {
        Message msn = convertMessage(message);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(msn.getContent(), true);
        helper.setTo(msn.getEmail());
        helper.setSubject("Notification from Rent-a-car service");
        helper.setFrom(env.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);
    }

    @Override
    public Message convertMessage(String message) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(message, Message.class);
        } catch (Exception e) {
            return null;
        }
    }
}
