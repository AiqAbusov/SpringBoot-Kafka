package com.dlq.aiq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducer {

    private static final String TOPIC = "users";


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void sendMessage(String message) {
        System.err.print(String.format("#### -&gt; Producing message -&gt; %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }

}
