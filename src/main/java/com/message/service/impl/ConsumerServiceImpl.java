package com.message.service.impl;

import com.message.service.ConsumerService;
import com.message.service.EmailService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private EmailService emailService;

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void consumeMessageFromRMQ() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException {

        System.out.println("Automatical???");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("4Es7xnijzf1DSgPxlcYcGTKhjifJQnIt");
        factory.setPassword("4Es7xnijzf1DSgPxlcYcGTKhjifJQnIt");
        factory.setHost("emu.rmq.cloudamqp.com");
        factory.setUri("amqp://asdlnfka:4Es7xnijzf1DSgPxlcYcGTKhjifJQnIt@emu.rmq.cloudamqp.com/asdlnfka");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("rabbitmq-message", false, false, false, null);
        //sending message

        channel.basicConsume("rabbitmq-message", true, (consumerTag, message) -> {
            String m = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println("I just received message" + m);
            try {
                this.emailService.sendMessageAsync(m);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }, consumerTag -> {
        });

    }
}
