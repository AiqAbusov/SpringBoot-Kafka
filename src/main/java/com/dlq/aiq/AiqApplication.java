package com.dlq.aiq;

import com.dlq.aiq.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class AiqApplication implements CommandLineRunner {

    @Autowired
	KafkaProducer kafkaProducer;

    public static void main(String[] args) {
        SpringApplication.run(AiqApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        kafkaProducer.sendMessage("Salam its Kafka Message");
    }
}
