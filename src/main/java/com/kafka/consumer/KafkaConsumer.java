package com.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.model.StudentDTO;
import com.kafka.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final StudentService studentService;

    @Autowired
    KafkaConsumer(StudentService studentService) {
        this.studentService = studentService;
    }

    @KafkaListener(topics = "test_topic", groupId = "test_group")
    public void consumer(String message) throws JsonProcessingException {
        StudentDTO studentDTO = new ObjectMapper().readValue(message,
                StudentDTO.class);
        studentService.saveStudent(studentDTO);
        System.out.println("Received Message in group foo: " + studentDTO.toString());
    }
}
