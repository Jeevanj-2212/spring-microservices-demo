package com.example.welcome_service.kafkaProducers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    // The name of the channel/folder where our events will live
    private static final String TOPIC = "user-login-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishLoginEvent(String name) {
        String jsonEvent = String.format("{\"username\":\"%s\", \"status\":\"LOGGED_IN\"}", name);
        System.out.println("--> Sending Event to Kafka: " + jsonEvent);

        // This fires the message asynchronously over the network to Docker
        this.kafkaTemplate.send(TOPIC, jsonEvent);
    }
}
