package com.message.service.impl;

import com.message.service.ProducerService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

@Service
public class ProducerServiceImpl implements ProducerService {
    @Override
    public String sentMessageToRMQ(String message) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("4Es7xnijzf1DSgPxlcYcGTKhjifJQnIt");
        factory.setPassword("4Es7xnijzf1DSgPxlcYcGTKhjifJQnIt");
        factory.setHost("emu.rmq.cloudamqp.com");
        factory.setUri("amqp://asdlnfka:4Es7xnijzf1DSgPxlcYcGTKhjifJQnIt@emu.rmq.cloudamqp.com/asdlnfka");
        System.out.println(message);
        try (Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();
            //red
            channel.queueDeclare("rabbitmq-message", false, false, false, null);
            //sending message
            channel.basicPublish("", "rabbitmq-message", false, null, message.getBytes());

            System.out.println("Message sent");
            return message;
        } catch (NullPointerException | IOException | TimeoutException e) {
            // see note 2
            return e.getMessage();

        }
    }
}
