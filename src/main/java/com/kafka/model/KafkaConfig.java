package com.kafka.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
@Configuration
public class KafkaConfig implements Serializable {
    private static final long serialVersionUID = 5244930773699584566L;

    @Value("${kafka.bootstrap.servers:localhost:9092}")
    private String bootStrapServers;

    @Value("${kafka.outbound.topic:test_topic}")
    private String outboundTopic;

    @Value("${kafka.consumer.group:test_group}")
    private String consumerGroup;

    @Value("${kafka.enable.auto.commit:true}")
    private String enableAutoCommit;

    @Value("${kafka.auto.offset.reset:earliest}")
    private String autoOffsetResetConfig;

    @Value("${kafka.auto.commit.interval.ms:1000}")
    private String autoCommitIntervalInMs;

    @Value("${kafka.session.time.out.ms:30000}")
    private String sessionTimeOutInMs;

    @Value("${kafka.connections.max.idle.ms:2047483647}")
    private String connectionMaxIdleMs;

    @Value("${kafka.max.poll.records:5}")
    private String maxPollRecords;

    @Value("${kafka.fetch.max.bytes:52428800}")
    private String fetchMaxBytes;

    @Value("${kafka.max.partition.fetch.bytes:5048576}")
    private String maxPartitionFetchBytes;

    @Value("${kafka.max.request.size:5028576}")
    private String maxRequestSize;
}
