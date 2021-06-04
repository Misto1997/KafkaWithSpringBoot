package com.kafka.controller;

import com.kafka.producer.KafkaPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("publisher")
public class PublisherController {

    private final KafkaPublisher kafkaPublisher;

    @Autowired
    PublisherController(KafkaPublisher kafkaPublisher) {
        this.kafkaPublisher = kafkaPublisher;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<String> conversationEngineAsync(@RequestBody String jsonBody) {
        kafkaPublisher.sendMessage(jsonBody);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }
}
