package com.kafka.producer;

import com.kafka.model.KafkaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaPublisher {

    private final KafkaConfig kafkaConfig;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    KafkaPublisher(KafkaConfig kafkaConfig, KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaConfig = kafkaConfig;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(kafkaConfig.getOutboundTopic(), msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + msg +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + msg + "] due to : " + ex.getMessage());
            }
        });
    }
}
