package com.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

@Service
@Slf4j
public class KafkaPublisher {

    private final KafkaTemplate kafkaTemplate;

    @Autowired
    KafkaPublisher(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public <T> void sendMessage(T record, String topicName) {
        String key = UUID.randomUUID().toString();
        ListenableFuture<SendResult<String, T>> future = kafkaTemplate.send(topicName, key, record);

        future.addCallback(new ListenableFutureCallback<SendResult<String, T>>() {

            @Override
            public void onSuccess(SendResult<String, T> result) {
                log.info("Sent message=[" + record +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=["
                        + record + "] due to : " + ex.getMessage());
            }
        });
    }
}
