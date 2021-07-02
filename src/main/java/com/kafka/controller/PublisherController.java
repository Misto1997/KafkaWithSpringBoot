package com.kafka.controller;

import com.kafka.model.KafkaConfig;
import com.kafka.model.Student;
import com.kafka.model.Teacher;
import com.kafka.producer.KafkaPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("publisher")
@Slf4j
public class PublisherController {

    private final KafkaPublisher kafkaPublisher;
    private final KafkaConfig kafkaConfig;

    @Autowired
    PublisherController(KafkaPublisher kafkaPublisher,
                        KafkaConfig kafkaConfig) {
        this.kafkaPublisher = kafkaPublisher;
        this.kafkaConfig = kafkaConfig;
    }

    @PostMapping(value = "student", produces = "application/json")
    public ResponseEntity<String> conversationEngineAsync(@RequestBody Student student) {
        log.info("Message received :{}", student.toString());
        kafkaPublisher.sendMessage(student, kafkaConfig.getOutboundStudentTopic());
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "teacher", produces = "application/json")
    public ResponseEntity<String> conversationEngineAsync1(@RequestBody Teacher teacher) {
        log.info("Message received :{}", teacher.toString());
        kafkaPublisher.sendMessage(teacher, kafkaConfig.getOutboundTeacherTopic());
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }
}
