package com.kafka.consumer;

import com.kafka.model.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private final KafkaConfig kafkaConfig;

    @Autowired
    KafkaConsumerConfig(KafkaConfig kafkaConfig) {
        this.kafkaConfig = kafkaConfig;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootStrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfig.getConsumerGroup());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaConfig.getEnableAutoCommit());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConfig.getAutoOffsetResetConfig());
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, kafkaConfig.getAutoCommitIntervalInMs());
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaConfig.getSessionTimeOutInMs());
        props.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, kafkaConfig.getConnectionMaxIdleMs());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaConfig.getMaxPollRecords());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, kafkaConfig.getFetchMaxBytes());
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, kafkaConfig.getFetchMaxBytes());
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
