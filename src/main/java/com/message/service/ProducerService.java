package com.message.service;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface ProducerService {
    String sentMessageToRMQ(String message) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException;
}
