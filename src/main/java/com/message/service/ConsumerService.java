package com.message.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public interface ConsumerService {
    void consumeMessageFromRMQ() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException;
}
