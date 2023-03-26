package com.dlq.aiq.service;

import com.dlq.aiq.constants.KafkaConstants;
import com.dlq.aiq.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass().getName());

    private final KafkaConstants kafkaConstants;

    public KafkaConsumer(KafkaConstants kafkaConstants) {
        this.kafkaConstants = kafkaConstants;
    }

    @RetryableTopic(
            attempts = "5",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            exclude = {CustomException.class}

    )
    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupId}")
    public void consume(String message) throws Exception {
        LOG.info("Kafka Listener Started Topic {}  Group ID {} Message {}", kafkaConstants.getTopic(),
                kafkaConstants.getGroupId(), message);
        throw new CustomException(message);
    }

    @DltHandler
    public void handleDlt(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        LOG.info("Message: {} handled by dlq topic: {}" + message + topic);
    }
}
