package com.message.service;

import com.message.model.Message;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMessageAsync(String message) throws MessagingException;

    Message convertMessage(String message);
}
