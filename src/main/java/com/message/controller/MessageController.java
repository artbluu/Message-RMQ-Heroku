package com.message.controller;

import com.message.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin("http://localhost:4200")
public class MessageController {

    @Autowired
    private ProducerService producerService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Email-Message service!";
    }

    @PostMapping(value = "/send", produces = "application/json", consumes = "application/json")
    public ResponseEntity sentMessageToRMQ(@RequestBody String message) {
        try {
            this.producerService.sentMessageToRMQ(message);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NullPointerException | NoSuchAlgorithmException | KeyManagementException | URISyntaxException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
    }

}
