package com.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.model.Student;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test_topic", groupId = "test_group")
    public void consumer(String message) throws JsonProcessingException {
        Student student = new ObjectMapper().readValue(message,
                Student.class);
        System.out.println("Received Message in group foo: " + student.toString());
    }
}
